package model.room.repositories;

import android.app.Application;

import api.MyRetrofit;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private MyRetrofit retrofit;
    public Account currentAccount;
    private static volatile LoginRepository INSTANCE;

    public static LoginRepository getLoginRepositoryInstance() { ;
        if (INSTANCE == null){
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    private LoginRepository(){
        retrofit = new MyRetrofit();
    }

    public void login(String username, String password){
        Call<Account> call = retrofit.api.logIn(username,password);
        call.enqueue(new Callback<Account>(){
            @Override
            public void onResponse (Call <Account> call, Response<Account> response){
                System.out.println("SUCCESS " + response.body());
                currentAccount = response.body();
                if(response.body() instanceof Customer) currentAccount.setRights("User");
                if(response.body() instanceof Employee) currentAccount.setRights("Supervisor");
                if(response.body() instanceof BusinessOwner) currentAccount.setRights("Owner");
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("Failed at Login");
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
                System.out.println(t.getStackTrace());

            }

        });
    }
}
