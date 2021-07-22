package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.MyRepository;

public class SaunaViewModel extends AndroidViewModel {
    public MyRepository repository;

    public SaunaViewModel (Application application) {
        super(application);
       repository = new MyRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() { return repository.getAllSaunas(); }
    public LiveData<List<DataPoint>> getAllDatapointsForASauna(Sauna sauna){
        repository.populateDatapointRepo(sauna);
        return repository.getAllDataPoints();}
    public void spinServo(Sauna sauna){
        //unimplemented
    }
}
