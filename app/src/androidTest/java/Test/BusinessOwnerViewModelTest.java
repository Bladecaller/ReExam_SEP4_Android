package Test;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.AccountRepository;
import viewmodel.BusinessOwnerViewModel;

import static org.junit.Assert.*;

public class BusinessOwnerViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    //@Mock
    BusinessOwnerViewModel vm;


    @Before
    public void setUp() throws Exception {
        vm = new BusinessOwnerViewModel(ApplicationProvider.getApplicationContext());

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCustomerAccount() {
        Customer cust = new Customer(2,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);
        Customer cust2 = new Customer(3,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);
        vm.repositoryAccount.accountInsert(cust2);
        System.out.println(vm.getCustomerTest().getUsername());
    }
}