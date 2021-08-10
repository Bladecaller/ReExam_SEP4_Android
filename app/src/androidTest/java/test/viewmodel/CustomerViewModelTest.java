package test.viewmodel;

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
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;
import viewmodel.CustomerViewModel;

public class CustomerViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    CustomerViewModel vm;
    Observer<List<Reservation>> observerReservation;
    List<Reservation> reservationList;

    Observer<List<Sauna>> observerSauna;
    List<Sauna> saunasList;

    SaunaRepository saunaRepository;
    ReservationRepository reservationRepository;


    @Before
    public void setUp() throws Exception {
        vm = new CustomerViewModel(ApplicationProvider.getApplicationContext());
        saunaRepository = new SaunaRepository(vm.getApplication());
        reservationRepository = new ReservationRepository(vm.getApplication());
        observerReservation = reservations -> reservationList = reservations;
        observerSauna = saunas -> saunasList = saunas;
    }

    @Test
    public void getReservationsForCustomer() throws InterruptedException {
       vm.getPersonalReservations(1).observeForever(observerReservation);
       reservationRepository.emptyAndPopulateReservationRepoAPI();
       Thread.sleep(10000);
        Assert.assertEquals(false,reservationList.isEmpty());
        Thread.sleep(2000);
        vm.getPersonalReservations(1).removeObserver(observerReservation);
    }

    @Test
    public void getSaunas() throws InterruptedException {
        vm.getAllSaunas().observeForever(observerSauna);
        saunaRepository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,saunasList.isEmpty());
        Thread.sleep(2000);
        vm.getAllSaunas().removeObserver(observerSauna);

    }

}