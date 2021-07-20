package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.MyRepository;

public class EmployeeViewModel extends AndroidViewModel {
    public MyRepository repository;

    private final LiveData<List<Sauna>> saunas;
    private final LiveData<List<Account>> accounts;

    public EmployeeViewModel (Application application) {
        super(application);
        repository = new MyRepository(application);
        saunas = repository.getAllSaunas();
        accounts = repository.getAccounts();
    }

    public LiveData<List<Sauna>> getAllSaunas() { return saunas; }
    public LiveData<List<Account>> getAllAccounts(){return accounts; }
    public void book(Reservation reservation){repository.createReservation(reservation);}
    public void openDoor(Sauna sauna){repository.openDoor(sauna);}
    public void notificationCheck(){ repository.checkNotifications();}
}
