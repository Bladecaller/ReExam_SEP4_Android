package viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import model.room.repositories.MyRepository;

public class LoginViewModel extends AndroidViewModel {
    public MyRepository repository;

    public LoginViewModel (Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public void login(String username, String password){ repository.login(username,password);}
    public int getCurrentAccountType(){return repository.currentAccount.getRights();}
}
