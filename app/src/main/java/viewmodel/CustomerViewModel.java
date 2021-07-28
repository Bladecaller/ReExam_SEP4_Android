package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class CustomerViewModel extends AndroidViewModel {
    private SaunaRepository repositorySauna;
    private ReservationRepository repositoryReservation;
    private Account currentAccount = LoginRepository.getLoginRepositoryInstance().currentAccount;

    public CustomerViewModel (Application application) {
        super(application);
        repositorySauna = new SaunaRepository(application);
        repositoryReservation = new ReservationRepository(application);
    }

    public LiveData<List<Sauna>> getAllSaunas() {
        return repositorySauna.getAllSaunas();
    }
    public void book(Reservation reservation){
        repositoryReservation.createReservationAPI(reservation);
    }
    public LiveData<List<Reservation>> getPersonalReservations(){
        return repositoryReservation.getReservationsForCustomer();
    }
    public Account getCurrentAccount(){
        return  currentAccount;
    }

}
