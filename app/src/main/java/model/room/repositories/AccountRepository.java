package model.room.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.AccountsDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Account.RightsEnumConverter;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private MyRetrofit retrofit;
    public AccountsDao accountsDao;
    private Account currentAccount;

    public AccountRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        accountsDao = db.accountsDao();
        LoginRepository lg = LoginRepository.getLoginRepositoryInstance();
        currentAccount = lg.currentAccount;
    }

    public void emptyAndPopulateAccountsRepoAPI(){
        Call<List<Account>> call = retrofit.api.getAllAccounts();
        call.enqueue(new Callback<List<Account>>(){
            @Override
            public void onResponse (Call <List<Account>> call, Response<List<Account>> response){
                System.out.println("SUCCESS " + response.body());
                emptyAccountRepo();
                List<Account> temp;
                temp = response.body();
                for(Account acc : temp){
                    accountInsert(acc);
                    Log.d("RESPONSE API",acc.getClass().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println("Failed at populateAccountsRepo");
            }

        });
    }

    public void addACustomerAccountAPI(Customer account){
        Call call = retrofit.api.createNewCustomerAccount(account.getUsername(),account.getPassword(), account.getRights(), account.getRoomNumber());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void addAEmployeeAccountAPI(Employee account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void addABusinessOwnerAccountAPI(BusinessOwner account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void removeASingleAccountAPI(int accountID){
        Call call = retrofit.api.removeUser(accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void setRightsAPI(int accountID, RightsEnum rightsEnum){
        Call call = retrofit.api.setRights(RightsEnumConverter.fromRightsEnumToInt(rightsEnum), accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setRights");
            }
        });

    }

    public void setThresholdsAPI(float CO2, float humidity, float temperature){
        Call call = retrofit.api.setThresholds(temperature,humidity,CO2);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                //update the sauna repo when calling this
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setThresholds");
            }
        });
    }
    //-----------------------------------------------------------------------------------------------

    public void accountInsert(Account account) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            if(account instanceof Customer){
                accountsDao.insertCustomer((Customer)account);
            }

            if(account instanceof Employee){
                accountsDao.insertEmployee((Employee) account);
            }

            if(account instanceof BusinessOwner){
                accountsDao.insertBusinessOwner((BusinessOwner) account);
            }
        });
    }

    //delete all accounts
    public void emptyAccountRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.deleteAllCustomers();
        });
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.deleteAllEmployees();
        });
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.deleteAllBusinessOwners();
        });
    }

    // return a list of all accounts to the viewmodel
    public LiveData<List<Customer>> getCustomers(){
        return accountsDao.getAllCustomers();
    }
    public LiveData<List<Employee>> getEmployees(){
        return accountsDao.getAllEmployees();
    }
    public LiveData<List<BusinessOwner>> getBusinessOwners(){
        return accountsDao.getAllBusinessOwners();
    }

    //--------Notifications-------------------------------------------------------------------------------------
    public void changeNotifications(){
        if(currentAccount instanceof Employee){
            if(((Employee) currentAccount).notifications==false){
                ((Employee) currentAccount).notifications=true;
            } else{
                ((Employee) currentAccount).notifications=false;
            }
        }
    }
}
