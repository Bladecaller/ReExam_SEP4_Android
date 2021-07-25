package Test;

import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;

import model.room.dao.AccountsDao;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.roomdatabase.MyRoomDatabase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class AccountsDaoInstrumentTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    MyRoomDatabase db;
    AccountsDao dao;
    @Mock
    Observer<List<Customer>> observer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //observer = mock(Observer.class);
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = MyRoomDatabase.getDatabase(context);
        dao = db.accountsDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void insert() {
        Customer cust = new Customer(0,"lily", "lilipass", RightsEnum.Customer, "coldTub", 15);

        //dao.getAllCustomers().observeForever(observer);

        dao.insertCustomer(cust);

        System.out.println(dao.getCustomerTest().getUsername());



    }
}