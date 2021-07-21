package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import model.room.repositories.MyRepository;

public class BusinessOwnerViewModel extends AndroidViewModel {
    public MyRepository repository;

    private final LiveData<List<Account>> accounts;
    public BusinessOwnerViewModel(@NonNull @NotNull Application application) {
        super(application);
        repository = new MyRepository(application);
        accounts = repository.getAllAccounts();
    }

    public void setRights(RightsEnum rights, int accountID){repository.setRights(accountID, rights); }
    public LiveData<List<Account>> getAccounts(){return repository.getAllAccounts();}
    public void addCustomerAccount(Customer account){repository.addACustomerAccount(account);}
    public void addEmployeeAccount(Employee account){repository.addAEmployeeAccount(account);}
    public void addBusinessOwnerAccount(BusinessOwner account){repository.addABusinessOwnerAccount(account);}
    public void removeAccount(int id){ repository.removeASingleAccount(id);}
    public void setThresholds(float CO2,float humidity,float temp){ repository.setTresholds(CO2, humidity, temp); }
}
