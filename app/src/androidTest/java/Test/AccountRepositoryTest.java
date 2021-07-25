package Test;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.AccountRepository;
import model.room.roomdatabase.MyRoomDatabase;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    //@Mock
    AccountRepository accountRepository;


    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);

        accountRepository = new AccountRepository(ApplicationProvider.getApplicationContext());
        //observer = mock(Observer.class);

    }
    @Test
    public void addACustomerAccount() {
        Customer cust2 = new Customer(3,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);
        accountRepository.accountInsert(cust2);
        System.out.println(accountRepository.getCustomerTEST().getUsername());
    }
}