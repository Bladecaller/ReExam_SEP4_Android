package test.dao;

import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;


import java.util.List;

import model.room.dao.AccountsDao;
import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.roomdatabase.MyRoomDatabase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

public class AccountsDaoTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    MyRoomDatabase db;
    AccountsDao dao;
    Observer<List<Customer>> observer;
    List<Customer> list;

    @Before
    public void setUp() throws Exception {
        observer = new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                list = customers;
            }
        };


        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
                context,
                MyRoomDatabase.class
        ).allowMainThreadQueries().build();
        dao = db.accountsDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void insertGetRemoveGet() {
        Customer cust = new Customer(0,"lily", "lilipass", 0, "coldTub", 15);
        dao.getAllCustomers().observeForever(observer);
        dao.insertCustomer(cust);
        System.out.println(list.get(0).getUsername());
        dao.deleteAllCustomers();
        System.out.println(list.isEmpty());

    }
}