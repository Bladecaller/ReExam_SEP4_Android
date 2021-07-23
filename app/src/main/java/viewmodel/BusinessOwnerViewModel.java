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
import model.room.repositories.AccountRepository;
import model.room.repositories.SaunaRepository;

public class BusinessOwnerViewModel extends AndroidViewModel {
    private AccountRepository repositoryAccount;
    private SaunaRepository repositorySauna;

    public BusinessOwnerViewModel(@NonNull @NotNull Application application) {
        super(application);
        repositoryAccount = new AccountRepository(application);
        repositorySauna = new SaunaRepository(application);
    }

    public void setRights(RightsEnum rights, int accountID){
        repositoryAccount.setRights(accountID, rights);
        repositoryAccount.populateAccountsRepo();
    }
    public LiveData<List<Account>> getAccounts(){return repositoryAccount.getAllAccounts();}
    public void addCustomerAccount(Customer account){
        repositoryAccount.addACustomerAccount(account);
        repositoryAccount.populateAccountsRepo();
    }
    public void addEmployeeAccount(Employee account){
        repositoryAccount.addAEmployeeAccount(account);
        repositoryAccount.populateAccountsRepo();
    }
    public void addBusinessOwnerAccount(BusinessOwner account){
        repositoryAccount.addABusinessOwnerAccount(account);
        repositoryAccount.populateAccountsRepo();
    }
    public void removeAccount(int id){
        repositoryAccount.removeASingleAccount(id);
        repositoryAccount.populateAccountsRepo();
    }
    public void setThresholds(float CO2,float humidity,float temp){
        repositoryAccount.setThresholds(CO2, humidity, temp);
        repositorySauna.populateSaunasRepo();
    }
}
