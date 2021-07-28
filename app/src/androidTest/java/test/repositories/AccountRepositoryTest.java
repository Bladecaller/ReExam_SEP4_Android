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

import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.AccountRepository;

public class AccountRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    //@Mock
    AccountRepository accountRepository;
    Observer<List<Customer>> observer;
    List<Customer> list;

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        accountRepository = new AccountRepository(ApplicationProvider.getApplicationContext());
        observer = new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                list = customers;
            }
        };
    }
    @Test
    public void addAndRemoveCustomerAccount() {
        Customer cust = new Customer(3,"lilian", "lilipass", RightsEnum.Customer, "coldTub", 15);

        accountRepository.getCustomers().observeForever(observer);

        accountRepository.accountInsert(cust);
        System.out.println(list.get(0).getUsername());
        
        accountRepository.emptyAccountRepo();
        System.out.println(list.isEmpty());

    }
}