package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Account.CurrentAccount;
import model.room.entity.Sauna.DataPoint;
import model.room.repositories.LoginRepository;
import viewmodel.LoginViewModel;
import viewmodel.SaunaViewModel;

import static org.junit.Assert.*;

public class LoginViewModelTest {
    LoginViewModel vm;
    List<CurrentAccount> list;
    Observer <List<CurrentAccount>> observer;
    LoginRepository repo;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        vm = new LoginViewModel(ApplicationProvider.getApplicationContext());
        repo = new LoginRepository(vm.getApplication());
        observer = new Observer<List<CurrentAccount>>() {
            @Override
            public void onChanged(List<CurrentAccount> currentAccounts) {
                list = currentAccounts;
            }
        };
    }

    @Test
    public void loginAndCheckRights() throws InterruptedException {
        vm.getCurrentAcc().observeForever(observer);
        vm.login("Owner", "Owner");
        Thread.sleep(5000);
        Assert.assertEquals("Owner",list.get(0).getRights().trim());
    }

}