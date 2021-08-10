package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import viewmodel.SaunaViewModel;

public class SaunaViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    SaunaViewModel vm;
    Observer<List<DataPoint>> observer;
    Observer<List<Sauna>> observer2;
    List<DataPoint> list;
    List<Sauna> list2;

    @Before
    public void setUp() throws Exception {
        vm = new SaunaViewModel(ApplicationProvider.getApplicationContext());
        observer = new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                list = dataPoints;
            }
        };
        observer2 = new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                list2 = saunas;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDataPoints() throws InterruptedException {
        vm.getAllDatapointsForASauna(2).observeForever(observer);
        vm.getAllDatapointsForASauna(2);
        Thread.sleep(10000);
        Assert.assertEquals(false,list.isEmpty());
    }

    @Test
    public void openDoor(){
        vm.spinServo(2);
        //not throwing an error passes
    }

    @Test
    public void getSaunas() throws InterruptedException {
        vm.getAllSaunas().observeForever(observer2);
        Thread.sleep(10000);
        Assert.assertEquals(false,list2.isEmpty());
    }
}