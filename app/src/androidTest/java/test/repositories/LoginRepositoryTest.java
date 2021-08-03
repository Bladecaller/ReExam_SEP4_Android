package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.Reservation;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;

import static org.junit.Assert.*;

public class LoginRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    LoginRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = LoginRepository.getLoginRepositoryInstance();
    }

    @Test
    public void loginAPI() throws InterruptedException {
        repository.login("bob", "bob");
        Thread.sleep(20000);
        System.out.println(repository.currentAccount.getRights());

    }
}