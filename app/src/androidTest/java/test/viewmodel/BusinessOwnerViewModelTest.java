package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.AccountRepository;
import model.room.repositories.SaunaRepository;
import viewmodel.BusinessOwnerViewModel;

public class BusinessOwnerViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    BusinessOwnerViewModel vm;
    Observer<List<Customer>> observer;
    Observer<List<Sauna>> observer2;
    List<Customer> list;
    List<Sauna>list2;
    AccountRepository repo;
    SaunaRepository repo2;

    @Before
    public void setUp() throws Exception {
        vm = new BusinessOwnerViewModel(ApplicationProvider.getApplicationContext());
        repo = new AccountRepository(vm.getApplication());
        repo2 = new SaunaRepository(vm.getApplication());

        observer = new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                list = customers;
            }
        };

        observer2 = new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list2 = saunas;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    //@Test
    public void getCustomersAccount() throws InterruptedException {
        vm.getCustomerAccounts().observeForever(observer);
        repo.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(30000);
        Assert.assertEquals(false,list.isEmpty());
        vm.getCustomerAccounts().removeObserver(observer);
    }
    @Test
    public void getSaunas() throws InterruptedException {
        vm.getSaunas().observeForever(observer2);
        repo2.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list2.isEmpty());
        vm.getSaunas().removeObserver(observer2);
    }

}