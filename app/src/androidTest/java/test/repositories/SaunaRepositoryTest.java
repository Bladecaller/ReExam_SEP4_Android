package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.ArrayList;
import java.util.List;

import model.room.entity.IntegerEntity;
import model.room.entity.Sauna.Sauna;
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


    //@Test
    public void populateAllSaunasAPI() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(30000);
        System.out.println("TEST initial size populate :"+list.size());

    }
    //@Test
    public void openDoor(){
        Sauna s = new Sauna(1,1,1,1,2,null);
        repository.openDoorAPI(s);
    }
    //@Test
    public void setThresholds() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(40000);
        System.out.println("TEST TEMP threshold before :"+list.get(list.size()-1).getTemperatureThreshold());

        repository.setThresholdsAPI(2,2,2,list.get(list.size()-1));
        Thread.sleep(40000);
        System.out.println("TEST TEMP threshold after :"+list.get(list.size()-1).getTemperatureThreshold());
    }
    //@Test
    public void notifications() throws InterruptedException {
        //List<IntegerEntity> listInt = new ArrayList<>();
        //repository.checkNotificationsAPI();
        //listInt = repository
        //Thread.sleep(10_000);
        //System.out.println("Saunas over the top :"+ listInt.size());
    }
}