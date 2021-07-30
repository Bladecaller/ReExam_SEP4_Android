package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Account.RightsEnumConverter;
import model.room.repositories.AccountRepository;
import model.room.repositories.DataPointRepository;
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
        repository = LoginRepository.getLoginRepositoryInstance();
        accRepo = new AccountRepository(application);
        bookRepo = new ReservationRepository(application);
        saunaRepo = new SaunaRepository(application);
    }

    public void login(String username, String password){
        accRepo.emptyAndPopulateAccountsRepoAPI();
        saunaRepo.emptyAndPopulateSaunasRepoAPI();
        bookRepo.emptyAndPopulateReservationRepoAPI();
        repository.login(username,password);
    }
    public RightsEnum getCurrentAccountType(){return (RightsEnumConverter.fromIntToRightsEnum(repository.currentAccount.getRights()));}
}
