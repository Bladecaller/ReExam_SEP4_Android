package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import api.MyRetrofit;
import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.MyRepository;
import model.room.repositories.WordRepository;

public class LoginViewModel extends AndroidViewModel {
    public MyRepository repository;

    public LoginViewModel (Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public void login(String username, String password){ repository.login(username,password);}
    public RightsEnum getCurrentAccountType(){return repository.currentAccount.getRights();}
}
