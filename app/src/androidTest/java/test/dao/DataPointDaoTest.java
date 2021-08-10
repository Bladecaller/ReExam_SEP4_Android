package test.dao;

import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;


import java.util.List;

import model.room.dao.DataPointDao;
import model.room.entity.Sauna.DataPoint;
import model.room.roomdatabase.MyRoomDatabase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

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
        DataPoint dp = new DataPoint(1,2,"12","3","4","5");
        dao.getAllDataPoints().observeForever(observer);
        dao.insert(dp);
        Assert.assertEquals("4",list.get(0).getCo2());
        dao.deleteAll();
        Assert.assertEquals(true,list.isEmpty());
    }
}