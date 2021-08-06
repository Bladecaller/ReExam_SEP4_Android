package model.room.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import api.MyRetrofit;
import model.room.dao.AccountsDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private MyRetrofit retrofit;
    public AccountsDao accountsDao;

    public AccountRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        accountsDao = db.accountsDao();

    }

    public void emptyAndPopulateAccountsRepoAPI(){
        Call<List<Account>> call = retrofit.api.getAllAccounts();
        call.enqueue(new Callback<List<Account>>(){
            @Override
            public void onResponse (Call <List<Account>> call, Response<List<Account>> response){
                emptyAccountRepo();
                System.out.println("SUCCESS " + response.body());
                for(Account acc : response.body()){
                    if(acc.getClass() == Customer.class) acc.setRights("User");
                    if(acc.getClass() == Employee.class) acc.setRights("Supervisor");
                    if(acc.getClass() == BusinessOwner.class) acc.setRights("Owner");
                    accountInsert(acc);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println("Failed at populateAccountsRepo");
                System.out.println(t.getMessage());
            }

        });
    }

    public void addACustomerAccountAPI(Customer account){
        Call call = retrofit.api.createNewCustomerAccount(account);//account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS " + response.body());
                System.out.println(call.request().body());
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
                System.out.println(t.getMessage());
            }
        });
    }

    public void addAEmployeeAccountAPI(Employee account){
        Call call = retrofit.api.createNewEmployeeAccount(account);//account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS " + response.body());
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void addABusinessOwnerAccountAPI(BusinessOwner account){
        Call call = retrofit.api.createNewBusinessOwnerAccount(account);//account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS " + response.body());
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
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                System.out.println("SUCCESS " + response.body());
                System.out.println("SUCCESS " + response.message());
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("Failed at removeASingleAccount");
            }
        });
    }

    public void setRightsAPI(Account account, RightsEnum enumm){
        account.setRights(enumm.name());
        Call call = null;
        if (account.getClass() == Customer.class){
            call = retrofit.api.setRightsOfACustomer(account.getUserID(),(Customer)account);
        }
        if (account.getClass() == Employee.class){
            call = retrofit.api.setRightsOfAnEmployee(account.getUserID(),(Employee)account);
        }
        if (account.getClass() == BusinessOwner.class){
            call = retrofit.api.setRightsOfABusinessOwner(account.getUserID(),(BusinessOwner)account);
        }
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS AT SetRights ?: " + response.message());
                emptyAndPopulateAccountsRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setRights");
                System.out.println(t.getMessage());

            }
        });

    }
    //-----------------------------------------------------------------------------------------------

    public void accountInsert(Account account) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {

            if(account.getClass() == Employee.class){
                accountsDao.insertEmployee((Employee) account);
            }

            if(account.getClass() == BusinessOwner.class){
                accountsDao.insertBusinessOwner((BusinessOwner) account);
            }

            if(account.getClass() == Customer.class){
                ((Customer) account).setRoomNumber(account.getUserID());
                accountsDao.insertCustomer((Customer)account);
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
    public void changeNotifications(Employee currentAccount){
            if(currentAccount.getNotifications()==false){
                currentAccount.setNotifications(true);
            } else{
               currentAccount.setNotifications(false);
            }
    }
}
