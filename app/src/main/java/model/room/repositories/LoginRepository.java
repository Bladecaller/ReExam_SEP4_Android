package model.room.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import api.MyRetrofit;
import model.room.dao.CurrentAccountDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.CurrentAccount;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private MyRetrofit retrofit;
    public CurrentAccountDao currentAccountDao;


    public LoginRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        currentAccountDao = db.currentAccountDao();

    }

    private LoginRepository(){
        retrofit = new MyRetrofit();
    }

    public void login(String username, String password){

        Call<CurrentAccount> call = retrofit.api.logIn(username,password);
        call.enqueue(new Callback<CurrentAccount>(){
            @Override
            public void onResponse (Call <CurrentAccount> call, Response<CurrentAccount> response){
                System.out.println("SUCCESS " + response.body());
                System.out.println("SUCCESS " + response.body().getRights());
                emptyRepo();
                accountInsert(response.body());
            }

            @Override
            public void onFailure(Call<CurrentAccount> call, Throwable t) {
                System.out.println("Failed at Login");
                System.out.println(t.getMessage());
            }

        });
    }

    public void accountInsert(CurrentAccount account) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            currentAccountDao.insertAccount(account);
        });
    }

    //delete all accounts
    public void emptyRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            currentAccountDao.deleteAll();
        });

    }

    // return a list of all accounts to the viewmodel
    public LiveData<List<CurrentAccount>> getCurrentAccount(){
        return currentAccountDao.getCurrentAccount();
    }

}
