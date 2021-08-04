package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;

import static org.junit.Assert.*;

public class LoginRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    LoginRepository repository;
    Observer<BusinessOwner> observer;
    Observer<Customer> observer1;
    Observer<Employee> observer2;
    BusinessOwner ow;
    Customer cust;
    Employee emp;
    @Before
    public void setUp() throws Exception {
        repository = new LoginRepository(ApplicationProvider.getApplicationContext());
        observer = new Observer<BusinessOwner>() {
            @Override
            public void onChanged(BusinessOwner businessOwner) {
                ow = businessOwner;
            }
        };
        observer1 = new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                cust = customer;
            }
        };
        observer2 = new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                emp = employee;
            }
        };
    }

    @Test
    public void loginOwnerAPI() throws InterruptedException {
        repository.getBusinessOwners().observeForever(observer);
        repository.login("bob", "bob");
        Thread.sleep(6000);
        System.out.println("NEW LOGIN TEST " + ow.getRights());

    }
    @Test
    public void loginCustomerAPI() throws InterruptedException {
        repository.getCustomers().observeForever(observer1);
        repository.login("Glorp", "111");
        Thread.sleep(6000);
        System.out.println("NEW LOGIN TEST " + cust.getRights());

    }
    @Test
    public void loginEmployeeAPI() throws InterruptedException {
        repository.getEmployees().observeForever(observer2);
        repository.login("user9", "user9");
        Thread.sleep(6000);
        System.out.println("NEW LOGIN TEST " + emp.getRights());

    }
}