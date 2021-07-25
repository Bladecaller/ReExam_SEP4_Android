package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import model.room.entity.Account.RightsEnum;
import model.room.repositories.LoginRepository;

public class LoginViewModel extends AndroidViewModel {
        private LoginRepository repository;

    public LoginViewModel (Application application) {
        super(application);
        repository = LoginRepository.getLoginRepositoryInstance();
    }

    public void login(String username, String password){ repository.login(username,password);}
    public RightsEnum getCurrentAccountType(){return (repository.currentAccount.getRights());}
}
