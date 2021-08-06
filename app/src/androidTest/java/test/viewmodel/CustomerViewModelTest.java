package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import viewmodel.CustomerViewModel;

public class CustomerViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    CustomerViewModel vm;
    Observer<List<Reservation>> observerReservation;
    List<Reservation> reservationList;

    Observer<List<Sauna>> observerSauna;
    List<Sauna> saunasList;


    @Before
    public void setUp() throws Exception {
        vm = new CustomerViewModel(ApplicationProvider.getApplicationContext());
       // vm.currentAccount = new Customer(11,"jack", "jackPass", "User");

        observerReservation = reservations -> reservationList = reservations;

        observerSauna = saunas -> saunasList = saunas;
    }
    @Test
    public void getReservationsForCustomer() throws InterruptedException {
       // vm.getPersonalReservations().observeForever(observerReservation);
        vm.repositoryReservation.emptyAndPopulateReservationRepoAPI();

        Thread.sleep(10000);
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL "+ reservationList.size());
        Thread.sleep(2000);
        //vm.getPersonalReservations().removeObserver(observerReservation);
    }

    @Test
    public void getSaunas() throws InterruptedException {
        vm.getAllSaunas().observeForever(observerSauna);
        vm.repositorySauna.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(10000);
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs "+ saunasList.size());
        Thread.sleep(2000);
        vm.getAllSaunas().removeObserver(observerSauna);

    }

}