package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.CurrentAccount;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.AccountRepository;
import model.room.repositories.LoginRepository;
import model.room.repositories.ReservationRepository;
import model.room.repositories.SaunaRepository;

public class LoginViewModel extends AndroidViewModel {
        private LoginRepository repository;
        private AccountRepository accRepo;
        private ReservationRepository bookRepo;
        private SaunaRepository saunaRepo;

    public LoginViewModel (Application application) {
        super(application);
        repository = new LoginRepository(application);
        accRepo = new AccountRepository(application);
        bookRepo = new ReservationRepository(application);
        saunaRepo = new SaunaRepository(application);
        accRepo.emptyAndPopulateAccountsRepoAPI();
        saunaRepo.emptyAndPopulateSaunasRepoAPI();
        bookRepo.emptyAndPopulateReservationRepoAPI();
    }

    public void login(String username, String password){
        repository.login(username,password);
    }
    public LiveData<List<CurrentAccount>> getCurrentAcc(){
        return  repository.getCurrentAccount();
    }
    public void deleteAll(){repository.emptyRepo();}
}
