package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.MyRepository;

public class BusinessOwnerViewModel extends AndroidViewModel {
    public MyRepository repository;

    private final LiveData<List<Account>> accounts;
    public BusinessOwnerViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository = new MyRepository(application);
        accounts = repository.getAccounts();
    }

    public void setRights(RightsEnum rights, Account account){repository.setRights(account, rights); }
    public LiveData<List<Account>> getAccounts(){return repository.getAccounts();}
    public void addAccount(Account account){repository.addASingleAccount(account);}
    public void removeAccount(Account account){ repository.removeASingleAccount(account);}
    public void setThresholds(float CO2,float humidity,float temp){ repository.setTresholds(CO2, humidity, temp); }
}
