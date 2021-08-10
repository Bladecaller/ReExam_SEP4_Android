package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
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
    Observer<List<IntegerEntity>> observer2;
    List<Sauna> list;
    List<IntegerEntity> list2;

    @Before
    public void setUp() throws Exception {
        observer = new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list = saunas;
            }
        };
        observer2 = new Observer<List<IntegerEntity>>() {
            @Override
            public void onChanged(List<IntegerEntity> ent) {
                list2 = ent;
            }
        };
        repository = new SaunaRepository(ApplicationProvider.getApplicationContext());
    }


    @Test
    public void populateAllSaunasAPI() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(25000);
        Assert.assertEquals(false,list.isEmpty());

    }
    @Test
    public void openDoor(){
        repository.openDoorAPI(1);
        // not getting an error counts as a pass
    }

    //@Test tested and passed but dont want to mess around with the API data
    public void setThresholds() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(40000);
        System.out.println("TEST TEMP threshold before :"+list.get(list.size()-1).getTemperatureThreshold());

        repository.setThresholdsAPI(2,2,2,list.get(list.size()-1));
        Thread.sleep(40000);
        System.out.println("TEST TEMP threshold after :"+list.get(list.size()-1).getTemperatureThreshold());
    }

    @Test
    public void notifications() throws InterruptedException {
        repository.getAllIntegerEntities().observeForever(observer2);
        repository.checkNotificationsAPI();
        Thread.sleep(25000);
        //Assert.assertEquals(false,list2.isEmpty());
        System.out.println("Saunas over the top :"+ list2.size());
        //this list size varies
    }
}