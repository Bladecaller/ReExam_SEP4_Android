package test.repositories;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import model.room.repositories.DataPointRepository;

public class DataPointRepositoryTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    DataPointRepository repository;
    Observer<List<DataPoint>> observer;
    List<DataPoint> list;


    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        observer = new Observer<List<DataPoint>>() {
            @Override
            public void onChanged(List<DataPoint> dataPoints) {
                list = dataPoints;
            }
        };
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        repository = new DataPointRepository(ApplicationProvider.getApplicationContext());

    }

    @Test
    public void populateDataPointAPI() throws InterruptedException {
        repository.getAllDataPoints().observeForever(observer);

        repository.emptyAndPopulateDatapointRepoAPI(1);
        Thread.sleep(20000);
        System.out.println("SUCCESSSSSSS   "+list.get(30).getDatapointID());
        System.out.println("SUCCESSSSSSS   "+list.get(30).getCo2());
        System.out.println("SUCCESSSSSSS   "+list.get(30).getHumidity());
        System.out.println("SUCCESSSSSSS   "+list.get(30).getSaunaID());
        System.out.println("SUCCESSSSSSS   "+list.get(30).getTemperature());
        System.out.println("SUCCESSSSSSS   "+list.get(30).getDateTime());

    }
}