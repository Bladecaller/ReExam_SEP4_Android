package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.repositories.AccountRepository;
import model.room.repositories.SaunaRepository;

public class BusinessOwnerViewModel extends AndroidViewModel {
    public AccountRepository repositoryAccount;
    private SaunaRepository repositorySauna;

    public BusinessOwnerViewModel(@NonNull @NotNull Application application) {
        super(application);
        repositoryAccount = new AccountRepository(application);
        repositorySauna = new SaunaRepository(application);
    }

    public void setRights(RightsEnum rights, int accountID){
        repositoryAccount.setRightsAPI(accountID, rights);
        repositoryAccount.emptyAndPopulateAccountsRepoAPI();
    }
    public LiveData<List<Customer>> getCustomerAccounts(){return repositoryAccount.getCustomers();}
    public LiveData<List<Employee>> getEmployeeAccounts(){return repositoryAccount.getEmployees();}
    public LiveData<List<BusinessOwner>> getBusinessOwnerAccounts(){return repositoryAccount.getBusinessOwners();}
    public void addCustomerAccount(Customer account){
        repositoryAccount.addACustomerAccountAPI(account);
        repositoryAccount.emptyAndPopulateAccountsRepoAPI();
    }
    public void addEmployeeAccount(Employee account){
        repositoryAccount.addAEmployeeAccountAPI(account);
        repositoryAccount.emptyAndPopulateAccountsRepoAPI();
    }
    public void addBusinessOwnerAccount(BusinessOwner account){
        repositoryAccount.addABusinessOwnerAccountAPI(account);
        repositoryAccount.emptyAndPopulateAccountsRepoAPI();
    }
    public void removeAccount(int id){
        repositoryAccount.removeASingleAccountAPI(id);
        repositoryAccount.emptyAndPopulateAccountsRepoAPI();
    }
    public void setThresholds(float CO2,float humidity,float temp){
        repositoryAccount.setThresholdsAPI(CO2, humidity, temp);
        repositorySauna.emptyAndPopulateSaunasRepoAPI();
    }
}
