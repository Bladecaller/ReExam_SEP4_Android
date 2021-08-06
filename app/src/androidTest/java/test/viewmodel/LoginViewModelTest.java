package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import viewmodel.LoginViewModel;
import viewmodel.SaunaViewModel;

import static org.junit.Assert.*;

public class LoginViewModelTest {
    LoginViewModel vm;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        vm = new LoginViewModel(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void loginAndCheckRights() throws InterruptedException {
        vm.login("bob", "bob");
        Thread.sleep(2000);
        //System.out.println("Test account type :"+vm.getCurrentAccountType());
    }

}