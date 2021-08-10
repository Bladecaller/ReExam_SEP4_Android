package test.viewmodel;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
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
import model.room.repositories.SaunaRepository;
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
    SaunaRepository saunaRepository;
    AccountRepository accountRepository;


    @Before
    public void setUp() throws Exception {
        observerCust = customers -> listCust = customers;
        observerSauna = saunas -> listSauna = saunas;
        observerReservation = reservations -> listReservations = reservations;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        vm = new EmployeeViewModel(ApplicationProvider.getApplicationContext());
        saunaRepository = new SaunaRepository(vm.getApplication());
        accountRepository = new AccountRepository(vm.getApplication());

    }

    //@Test
    public void getAccCustomerAPI() throws InterruptedException {
       vm.getCustomers().observeForever(observerCust);
        accountRepository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(20000);
        Assert.assertEquals(false,listCust.isEmpty());
        vm.getCustomers().removeObserver(observerCust);
    }

    //@Test
    public void getSaunasAPI() throws InterruptedException {
        vm.getAllSaunas().observeForever(observerSauna);
        saunaRepository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(25000);
        Assert.assertEquals(false,listSauna.isEmpty());
        vm.getAllSaunas().removeObserver(observerSauna);
    }
}