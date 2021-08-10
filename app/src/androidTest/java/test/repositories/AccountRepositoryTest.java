package test.repositories;

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
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.repositories.AccountRepository;
import model.room.repositories.DataPointRepository;

public class AccountRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    AccountRepository repository;
    Observer<List<Customer>> observer;
    Observer<List<Employee>> observer1;
    Observer<List<BusinessOwner>> observer2;
    List<Customer> list;
    List<Employee> list1;
    List<BusinessOwner> list2;


    @Before
    public void setUp() throws Exception {
        observer = customers -> list = customers;
        observer1 = employees -> list1 = employees;
        observer2 = businessOwners -> list2 = businessOwners;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        repository = new AccountRepository(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void populateAccCustomerAPI() throws InterruptedException {
        repository.getCustomers().observeForever(observer);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list.isEmpty());
        for(Customer cust : list){
            Assert.assertEquals(false,cust.getRights().isEmpty());
        }
        repository.getCustomers().removeObserver(observer);
    }

    @Test
    public void populateAccEmployeeAPI() throws InterruptedException {
        repository.getEmployees().observeForever(observer1);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list1.isEmpty());
        for(Employee emp : list1){
            Assert.assertEquals(false,emp.getRights().isEmpty());
        }
        repository.getEmployees().removeObserver(observer1);
    }

    @Test
    public void populateAccBusinessAPI() throws InterruptedException {
        repository.getBusinessOwners().observeForever(observer2);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list2.isEmpty());
        for(BusinessOwner bus : list2){
            Assert.assertEquals(false,bus.getRights().isEmpty());
        }
        repository.getBusinessOwners().removeObserver(observer2);
    }

    //@Test passed once
    public void addCustomerAccount() throws InterruptedException {

        repository.getCustomers().observeForever(observer);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list.isEmpty());
        int size = list.size();

        Customer cust = new Customer(14,"user14","user14","User");
        repository.addACustomerAccountAPI(cust);
        Thread.sleep(5000);
        Assert.assertEquals(size+1,list.size());
        repository.getCustomers().removeObserver(observer);
    }
    //@Test passed once
    public void removeAccount() throws  InterruptedException{

        repository.getCustomers().observeForever(observer);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(10000);
        Assert.assertEquals(false,list.isEmpty());
        int size = list.size();

        repository.removeASingleAccountAPI(6666);
        Thread.sleep(10000);
        Assert.assertEquals(size-1,list.size());
        repository.getCustomers().removeObserver(observer);
    }
}