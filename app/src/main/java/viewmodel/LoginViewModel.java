package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import model.room.entity.Account.RightsEnum;
import model.room.entity.Account.RightsEnumConverter;
import model.room.repositories.LoginRepository;
import model.room.repositories.MyRepository;

public class LoginViewModel extends AndroidViewModel {
        private LoginRepository repository;

    public LoginViewModel (Application application) {
        super(application);
        repository = LoginRepository.getLoginRepositoryInstance();
    }

    public void login(String username, String password){ repository.login(username,password);}
    public RightsEnum getCurrentAccountType(){return RightsEnumConverter.fromIntToRightsEnum(repository.currentAccount.getRights());}
}
