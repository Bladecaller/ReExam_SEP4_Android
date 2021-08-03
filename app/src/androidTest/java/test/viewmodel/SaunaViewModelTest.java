package test.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import viewmodel.SaunaViewModel;

public class SaunaViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    //@Mock
    SaunaViewModel vm;
    Observer<List<DataPoint>> observer;
    List<DataPoint> list;

    @Before
    public void setUp() throws Exception {
        vm = new SaunaViewModel(ApplicationProvider.getApplicationContext());
        observer = new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                list = dataPoints;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addAndRemoveSauna() throws InterruptedException {
        vm.getAllDatapointsForASauna(2).observeForever(observer);
        vm.getAllDatapointsForASauna(2);
        Thread.sleep(10000);
        System.out.println("CO2 lvl "+list.get(0).getCo2());

        vm.repositoryData.emptyDataRepo();
        Thread.sleep(2000);
        System.out.println(list.isEmpty());
    }
}