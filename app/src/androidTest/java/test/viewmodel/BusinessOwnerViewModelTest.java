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
    public void addCustomerAccount() {
        Customer cust = new Customer(2,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);
        Customer cust2 = new Customer(3,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);

        vm.getCustomerAccounts().observeForever(observer);

        vm.repositoryAccount.accountInsert(cust);
        vm.repositoryAccount.accountInsert(cust2);
        System.out.println(list.size());

        vm.repositoryAccount.emptyAccountRepo();
        System.out.println(list.size());

    }
}