package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.CurrentAccount;
import model.room.repositories.AccountRepository;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class LoginViewModel extends AndroidViewModel {
        private LoginRepository loginRepo;
        private AccountRepository accRepo;
        private ReservationRepository bookRepo;
        private SaunaRepository saunaRepo;

    public LoginViewModel (Application application) {
        super(application);
        loginRepo = new LoginRepository(application);
        accRepo = new AccountRepository(application);
        bookRepo = new ReservationRepository(application);
        saunaRepo = new SaunaRepository(application);
    }

    public void login(String username, String password){
        loginRepo.emptyRepo();
        loginRepo.login(username,password);
        accRepo.emptyAndPopulateAccountsRepoAPI();
        saunaRepo.emptyAndPopulateSaunasRepoAPI();
        bookRepo.emptyAndPopulateReservationRepoAPI();
    }

    public LiveData<List<CurrentAccount>> getCurrentAcc() {
        return loginRepo.getCurrentAccountList();
    }
}
