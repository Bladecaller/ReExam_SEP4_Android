package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.MyRepository;

public class CustomerViewModel extends AndroidViewModel {
    public MyRepository repository;

    private final LiveData<List<Sauna>> saunas;

    public CustomerViewModel (Application application) {
        super(application);

        repository = new MyRepository(application);
        saunas = repository.getAllSaunas();
    }

    public LiveData<List<Sauna>> getAllSaunas() { return saunas; }
    public void book(Reservation reservation){ repository.createReservation(reservation);}
    public LiveData<List<Reservation>> getPersonalReservations(){ return repository.getPersonalReservations();}

}
