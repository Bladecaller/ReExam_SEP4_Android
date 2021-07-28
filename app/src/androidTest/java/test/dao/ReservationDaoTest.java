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
import model.room.dao.ReservationDao;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.DataPoint;
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
        DataPoint dp = new DataPoint(1,2,3,4,5);
        Reservation book = new Reservation(1,11,12,4,"11:20","11:35");
        dao.getReservationsByCustomerId(book.getCustomerId()).observeForever(observer);
        dao.insertReservation(book);
        System.out.println(list.get(0).getBookTimeFrom());
        dao.deleteAll();
        System.out.println(list.isEmpty());
    }

    @Test
    public void insertGetALLRemoveGet(){
        Reservation book = new Reservation(1,11,12,4,"11:20","11:35");
        dao.getAllReservations().observeForever(observer);
        dao.insertReservation(book);
        System.out.println(list.get(0).getBookTimeFrom());
        dao.deleteAll();
        System.out.println(list.isEmpty());
    }
}