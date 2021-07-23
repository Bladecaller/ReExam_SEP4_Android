package model.room.repositories;

import android.app.Application;

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
    private AccountsDao accountsDao;

    public AccountRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        accountsDao = db.accountsDao();
    }

    public void populateAccountsRepo(){
        Call<List<Account>> call = retrofit.api.getAllAccounts();
        call.enqueue(new Callback<List<Account>>(){
            @Override
            public void onResponse (Call <List<Account>> call, Response<List<Account>> response){
                System.out.println("SUCCESS " + response.body());
                emptyAccountRepo();
                List<Account> temp;
                Account[] accArray = retrofit.gson.fromJson(response.body().toString(), Account[].class);
                temp = Arrays.asList(accArray);
                for(Account acc : temp){
                    accountInsert(acc);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println("Failed at populateAccountsRepo");
            }

        });
    }

    public void addACustomerAccount(Customer account){
        Call call = retrofit.api.createNewCustomerAccount(account.getUsername(),account.getPassword(), account.getRights(), account.getRoomNumber());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void addAEmployeeAccount(Employee account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void addABusinessOwnerAccount(BusinessOwner account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void removeASingleAccount(int accountID){
        Call call = retrofit.api.removeUser(accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void setRights(int accountID, RightsEnum rightsEnum){
        Call call = retrofit.api.setRights(RightsEnumConverter.fromRightsEnumToInt(rightsEnum), accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setRights");
            }
        });

    }

    public void setThresholds(float CO2, float humidity, float temperature){
        Call call = retrofit.api.setThresholds(temperature,humidity,CO2);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
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
            accountsDao.insert(account);
        });
    }

    //delete all accounts
    public void emptyAccountRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.deleteAll();
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
    public LiveData<List<Account>> getAllAccounts(){ return accountsDao.getAllAccounts(); }
}
