package test.repositories;

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
        //MockitoAnnotations.initMocks(this);
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
        Thread.sleep(25000);
        System.out.println("TEST size initial customer"+ list.size());
        for(Customer cust : list){
            System.out.println(cust.getUsername()+ " rights: " +cust.getRights());
        }
        repository.getCustomers().removeObserver(observer);

    }

    @Test
    public void populateAccEmployeeAPI() throws InterruptedException {
        repository.getEmployees().observeForever(observer1);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(20000);
        System.out.println("TEST size initial employee "+ list1.size());
        for(Employee cust : list1){
            System.out.println(cust.getUsername()+ " rights: " +cust.getRights());
        }
        repository.getEmployees().removeObserver(observer1);

    }

    @Test
    public void populateAccBusinessAPI() throws InterruptedException {
        repository.getBusinessOwners().observeForever(observer2);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(25000);
        System.out.println("TEST size initial owner  "+ list2.size());
        for(BusinessOwner cust : list2){
            System.out.println(cust.getUsername()+ " rights: " +cust.getRights());
        }
        repository.getBusinessOwners().removeObserver(observer2);
    }

    @Test
    public void addCustomerAccount() throws InterruptedException {

        repository.getCustomers().observeForever(observer);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(15000);
        System.out.println("TEST size before adding  "+ list.size());

        Customer cust = new Customer(10,"user14","user14","User");
        repository.addACustomerAccountAPI(cust);
        Thread.sleep(15000);
        System.out.println("TEST size after adding  "+ list.size());
        repository.getCustomers().removeObserver(observer);
    }
    @Test
    public void removeAccount() throws  InterruptedException{

        repository.getCustomers().observeForever(observer);
        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(15000);
        System.out.println("TEST size before remove  "+ list.size());

        repository.removeASingleAccountAPI(10);
        Thread.sleep(15000);
        System.out.println("TEST size after remove  "+ list.size());
        repository.getCustomers().removeObserver(observer);
    }
    @Test
    public void setRights() throws  InterruptedException{
        repository.getCustomers().observeForever(observer);

        repository.emptyAndPopulateAccountsRepoAPI();
        Thread.sleep(30000);
        System.out.println("Test size before setting rights to another type :"+ list.size());

        repository.setRightsAPI(list.get(list.size()-1), RightsEnum.Supervisor);
        Thread.sleep(30000);
        System.out.println("Test size after setting rights to another type :"+list.size());
    }
}