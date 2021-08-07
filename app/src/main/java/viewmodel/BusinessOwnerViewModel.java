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

    public void setRights(Account account, RightsEnum enumm){
        repositoryAccount.setRightsAPI(account, enumm);
    }

    public LiveData<List<Customer>> getCustomerAccounts(){
        return repositoryAccount.getCustomers();
    }

    public LiveData<List<Employee>> getEmployeeAccounts(){
        return repositoryAccount.getEmployees();
    }

    public LiveData<List<BusinessOwner>> getBusinessOwnerAccounts(){
        return repositoryAccount.getBusinessOwners();
    }

    public void addCustomerAccount(Customer account){
        repositoryAccount.addACustomerAccountAPI(account);
    }

    public void addEmployeeAccount(Employee account){
        repositoryAccount.addAEmployeeAccountAPI(account);
    }

    public void addBusinessOwnerAccount(BusinessOwner account){
        repositoryAccount.addABusinessOwnerAccountAPI(account);
    }

    public void removeAccount(int id){
        repositoryAccount.removeASingleAccountAPI(id);
    }

    public void setThresholds(float CO2, float humidity, float temp, Sauna sauna){
        repositorySauna.setThresholdsAPI(CO2, humidity, temp, sauna);
        repositorySauna.emptyAndPopulateSaunasRepoAPI();
    }

    public LiveData<List<Sauna>> getSaunas(){
        return repositorySauna.getAllSaunas();
    }
}
