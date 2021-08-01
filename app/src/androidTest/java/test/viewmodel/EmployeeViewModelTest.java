package test.viewmodel;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.AccountRepository;
import viewmodel.EmployeeViewModel;

import static org.junit.Assert.*;

public class EmployeeViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    EmployeeViewModel vm;
    Observer<List<Customer>> observerCust;
    Observer<List<Sauna>> observerSauna;
    Observer<List<Reservation>> observerReservation;
    List<Customer> listCust;
    List<Sauna> listSauna;
    List<Reservation> listReservations;


    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        observerCust = customers -> listCust = customers;
        observerSauna = saunas -> listSauna = saunas;
        observerReservation = reservations -> listReservations = reservations;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        vm = new EmployeeViewModel(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getAccCustomerAPI() throws InterruptedException {
       vm.getCustomers().observeForever(observerCust);
        vm.repositoryAccount.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(20000);
        System.out.println("customers !!!!1 "+ listCust.size());
        vm.getCustomers().removeObserver(observerCust);

    }

    @Test
    public void getSaunasAPI() throws InterruptedException {
        vm.getAllSaunas().observeForever(observerSauna);
        vm.repositoryAccount.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(20000);
        System.out.println("saunas !!!!1 "+ listSauna.size());
        vm.getAllSaunas().removeObserver(observerSauna);

    }
}