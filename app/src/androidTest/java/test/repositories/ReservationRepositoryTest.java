package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.repositories.ReservationRepository;

public class ReservationRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    ReservationRepository repository;
    Observer<List<Reservation>> observer;
    List<Reservation> list;

    @Before
    public void setUp() throws Exception {
        observer = new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                list = reservations;
            }
        };
        repository = new ReservationRepository(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void populateReservationsAPI() throws InterruptedException {
        repository.getReservationsForCurrentAccount(1).observeForever(observer);
        repository.emptyAndPopulateReservationRepoAPI();
        Thread.sleep(25000);
        Assert.assertEquals(false,list.isEmpty());
    }
    //@Test one time test
    public void addReservationAPI() throws InterruptedException{
        repository.getReservationsForCurrentAccount(1).observeForever(observer);
        repository.emptyAndPopulateReservationRepoAPI();
        Thread.sleep(15000);
        Assert.assertEquals(false,list.isEmpty());
        int size = list.size();
        Reservation book = new Reservation(1,3,"14:30","16:00");
        repository.createReservationAPI(book);
        Thread.sleep(15000);
        Assert.assertEquals(size+1,list.size());
        repository.getReservationsForCurrentAccount(1).removeObserver(observer);
    }
}