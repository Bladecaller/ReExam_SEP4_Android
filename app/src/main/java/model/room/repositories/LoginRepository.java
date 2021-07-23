package model.room.repositories;

import android.app.Application;

import api.MyRetrofit;
import model.room.entity.Account.Account;
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
        final Account[] acc = {null};
        Call<Account> call = retrofit.api.logIn(username,password);
        call.enqueue(new Callback<Account>(){
            @Override
            public void onResponse (Call <Account> call, Response<Account> response){
                System.out.println("SUCCESS " + response.body());
                acc[0] = retrofit.gson.fromJson(response.body().toString(), Account.class);
                currentAccount = acc[0];
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("Failed at Login");
            }

        });
    }
}
