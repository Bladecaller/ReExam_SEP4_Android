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

import model.room.dao.ReservationDao;
import model.room.entity.Account.Reservation;
import model.room.roomdatabase.MyRoomDatabase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

public class ReservationDaoTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    MyRoomDatabase db;
    ReservationDao dao;
    Observer<List<Reservation>> observer;
    List<Reservation> list;

    @Before
    public void setUp() throws Exception {
        //observer = mock(Observer.class);

        observer = new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                list = reservations;
            }
        };
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
                context,
                MyRoomDatabase.class
        ).allowMainThreadQueries().build();
        dao = db.reservationDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void insertGetRemoveGet(){
        Reservation book = new Reservation(11,12,"11:20","11:35");
        dao.getReservationsByCustomerId(book.getUserID()).observeForever(observer);
        dao.insertReservation(book);
        Assert.assertEquals("11:20",list.get(0).getFromDateTime());
        dao.deleteAll();
        Assert.assertEquals(true,list.isEmpty());
    }
}