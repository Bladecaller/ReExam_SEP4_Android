package Test;

import android.app.Application;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.repositories.AccountRepository;
import model.room.repositories.DataPointRepository;
import model.room.roomdatabase.MyRoomDatabase;

import static org.junit.Assert.*;

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
    public void addAndRemoveCustomerAccount() {
        DataPoint dp = new DataPoint(1,2,3,4,5);

        repository.getAllDataPoints().observeForever(observer);

        repository.datapointInsert(dp);
        System.out.println(list.get(0).getCO2());

        repository.emptyDataRepo();
        System.out.println(list.isEmpty());

    }
}