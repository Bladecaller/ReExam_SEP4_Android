package test.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.List;

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


    @Test
    public void populateAllSaunasAPI() throws InterruptedException {
        repository.getAllSaunas().observeForever(observer);

        repository.emptyAndPopulateSaunasRepoAPI();
        Thread.sleep(20000);
        System.out.println(list.size());
        System.out.println(list.get(2));

    }

}