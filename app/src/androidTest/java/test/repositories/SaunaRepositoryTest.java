package test.repositories;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.DataPointRepository;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class SaunaRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    SaunaRepository repository;
    Observer<List<Sauna>> observer;
    List<Sauna> list;

    @Before
    public void setUp() throws Exception {
        observer = new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list = saunas;
            }
        };
        repository = new SaunaRepository(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void addAndRemoveSaunas() {
        Sauna sauna = new Sauna(1,2,"11:10","12:00",true,12,12,4);
        repository.getAllSaunas().observeForever(observer);

        repository.saunaInsert(sauna);
        System.out.println(list.get(0).getReservedTimeTo());

        repository.emptySaunaRepo();
        System.out.println(list.isEmpty());
    }

    @Test
    public void populateAllSaunasAPI() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(10000);
        System.out.println(list.get(2).getReservedTimeTo());

    }

}