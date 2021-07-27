package Test;

import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.Collections;
import java.util.List;

import model.room.dao.AccountsDao;
import model.room.dao.DataPointDao;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.roomdatabase.MyRoomDatabase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class DataPointDaoTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    MyRoomDatabase db;
    DataPointDao dao;
    Observer<List<DataPoint>> observer;
    List<DataPoint> list;

    @Before
    public void setUp() throws Exception {
        //observer = mock(Observer.class);

        observer = new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                list = dataPoints;
            }
        };
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
                context,
                MyRoomDatabase.class
        ).allowMainThreadQueries().build();
        dao = db.dataPointDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void insertGetRemoveGet(){
        DataPoint dp = new DataPoint(1,2,3,4,5);
        dao.getAllDataPoints().observeForever(observer);
        dao.insert(dp);
        System.out.println(list.get(0).getCO2());
        dao.deleteAll();
        System.out.println(list.isEmpty());
    }
}