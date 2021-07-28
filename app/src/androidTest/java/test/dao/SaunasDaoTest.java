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

import model.room.dao.DataPointDao;
import model.room.dao.SaunasDao;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.roomdatabase.MyRoomDatabase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

public class SaunasDaoTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    MyRoomDatabase db;
    SaunasDao dao;
    Observer<List<Sauna>> observer;
    List<Sauna> list;

    @Before
    public void setUp() throws Exception {
        //observer = mock(Observer.class);

        observer = new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list = saunas;
            }
        };
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
                context,
                MyRoomDatabase.class
        ).allowMainThreadQueries().build();
        dao = db.saunaDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void insertGetRemoveGet(){
        Sauna sauna = new Sauna(1,2,"11:10","12:00",true,12,12,4);
        dao.getAllSaunas().observeForever(observer);
        dao.insert(sauna);
        System.out.println(list.get(0).getReservedTimeTo());
        dao.deleteAll();
        System.out.println(list.isEmpty());
    }
}