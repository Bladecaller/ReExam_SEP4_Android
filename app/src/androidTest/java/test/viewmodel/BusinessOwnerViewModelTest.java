package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import viewmodel.BusinessOwnerViewModel;

public class BusinessOwnerViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    //@Mock
    BusinessOwnerViewModel vm;
    Observer<List<Customer>> observer;
    List<Customer> list;

    @Before
    public void setUp() throws Exception {
        vm = new BusinessOwnerViewModel(ApplicationProvider.getApplicationContext());
        observer = new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                list = customers;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCustomersAccount() throws InterruptedException {
        vm.repositoryAccount.emptyAndPopulateAccountsRepoAPI();
        vm.getCustomerAccounts().observeForever(observer);


        Thread.sleep(10000);
        System.out.println(list.get(0).getRoomNumber());

        vm.repositoryAccount.emptyAccountRepo();
        Thread.sleep(2000);
        System.out.println(list.size());

    }
}