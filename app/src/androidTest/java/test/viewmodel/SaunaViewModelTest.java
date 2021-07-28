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
import model.room.entity.Sauna.Sauna;
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
    public void addAndRemoveSauna() {
        DataPoint dp = new DataPoint(1,2,3,4,5);
        Sauna sauna = new Sauna(2,2,"1","2",true,1,1,1);
        vm.getAllDatapointsForASauna(sauna.getId()).observeForever(observer);

        vm.repositoryData.datapointInsert(dp);
        System.out.println(list.get(0).getCO2());

        vm.repositoryData.emptyDataRepo();
        System.out.println(list.isEmpty());
    }
}