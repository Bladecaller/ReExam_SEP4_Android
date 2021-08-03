package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

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
        repository.getAllReservations().observeForever(observer);

        repository.emptyAndPopulateReservationRepoAPI();
        Thread.sleep(20000);
        System.out.println(list.get(2).getToDateTime());

    }
}