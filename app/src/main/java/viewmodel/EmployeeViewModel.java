package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.AccountRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class EmployeeViewModel extends AndroidViewModel {
    public SaunaRepository repositorySauna;
    public ReservationRepository repositoryReservation;
    public AccountRepository repositoryAccount;

    public EmployeeViewModel (Application application) {
        super(application);
        repositorySauna = new SaunaRepository(application);
        repositoryReservation = new ReservationRepository(application);
        repositoryAccount = new AccountRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() {
        repositorySauna.emptyAndPopulateSaunasRepoAPI();
        return repositorySauna.getAllSaunas(); }

    public LiveData<List<Customer>> getCustomers(){
        return repositoryAccount.getCustomers(); }

    public void book(Reservation reservation){
        repositoryReservation.createReservationAPI(reservation);
    }

    public void openDoor(Sauna sauna){
        repositorySauna.openDoorAPI(sauna);
    }

    public void changeNotificationsStatus(Employee currentAccount){repositoryAccount.changeNotifications(currentAccount);};

    public List<Integer> notificationCheck(){ return repositorySauna.checkNotificationsAPI();}
}
