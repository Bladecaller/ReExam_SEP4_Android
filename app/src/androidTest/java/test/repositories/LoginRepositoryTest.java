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
import model.room.entity.Account.CurrentAccount;
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
    Observer<List<CurrentAccount>> observer;
    List<CurrentAccount> list;
    @Before
    public void setUp() throws Exception {
        repository = new LoginRepository(ApplicationProvider.getApplicationContext());
        observer = new Observer<List<CurrentAccount>>() {
            @Override
            public void onChanged(List<CurrentAccount> ca) {
                list = ca;
            }
        };
    }


    @Test
    public void loginAPI() throws InterruptedException {
        repository.getCurrentAccount().observeForever(observer);
        repository.login("user9", "user9");
        Thread.sleep(6000);
        System.out.println("NEW LOGIN TEST " + list.get(0).getRights());

    }
}