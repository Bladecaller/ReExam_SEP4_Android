package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.entity.WordEntity;
import model.room.repositories.MyRepository;
import model.room.repositories.WordRepository;

public class SaunaViewModel extends AndroidViewModel {
    public MyRepository repository;

    private final LiveData<List<Sauna>> saunas;

    public SaunaViewModel (Application application) {
        super(application);
       repository = new MyRepository(application);
        saunas = repository.getAllSaunas();
    }

    public LiveData<List<Sauna>> getAllSaunas() { return saunas; }
    public LiveData<List<DataPoint>> getAllDatapointsForASauna(Sauna sauna){
        repository.populateDatapointRepo(sauna);
        return repository.getAllDataPoints();}
}
