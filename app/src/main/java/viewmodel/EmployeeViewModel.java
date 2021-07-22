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

    public EmployeeViewModel (Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() {
        repository.populateSaunasRepo();
        return repository.getAllSaunas(); }
    public LiveData<List<Customer>> getCustomers(){
        repository.populateAccountsRepo();
        return repository.getCustomers(); }
    public void book(Reservation reservation){
        repository.createReservation(reservation);
        repository.populateReservationRepo();
    }
    public void openDoor(Sauna sauna){
        repository.openDoor(sauna);
        repository.populateSaunasRepo();
    }
    public void changeNotificationsStatus(){repository.changeNotifications();};
    public List<Sauna> notificationCheck(){ return repository.checkNotifications();}
}
