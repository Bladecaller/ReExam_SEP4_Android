package model.room.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.SaunasDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.Employee;
import model.room.entity.Sauna.Sauna;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaunaRepository {
    private MyRetrofit retrofit;

    private SaunasDao saunasDao;

    private Account currentAccount;

    public SaunaRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        saunasDao = db.saunaDao();
        LoginRepository lg = LoginRepository.getLoginRepositoryInstance();
        currentAccount = lg.currentAccount;
    }

    public void emptyAndPopulateSaunasRepoAPI(){
        Call<List<Sauna>> call = retrofit.api.getAllSaunas();
        call.enqueue(new Callback<List<Sauna>>(){
            @Override
            public void onResponse (Call <List<Sauna>> call, Response<List<Sauna>> response){
                System.out.println("SUCCESS " + response.body());
                emptySaunaRepo();
                List<Sauna> temp;
                Sauna[] array = retrofit.gson.fromJson(response.body().toString(), Sauna[].class);
                temp = Arrays.asList(array);
                for(Sauna obj : temp){
                    saunaInsert(obj);
                }
            }

            @Override
            public void onFailure(Call<List<Sauna>> call, Throwable t) {
                System.out.println("Failed at populateSaunasRepo");
            }

        });
    }

    public void openDoorAPI(Sauna sauna){
        Call call = retrofit.api.openTheDoor(sauna.getId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateSaunasRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at OpenDoor");
            }
        });
    }

    public List<Sauna> checkNotificationsAPI(){
        final Integer[] temp = {null};
        List<Sauna> notifiedSaunas = null;
        Call<List<Integer>> call = retrofit.api.checkNotification();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                emptyAndPopulateSaunasRepoAPI();
                List<Integer> temp;
                Integer[] array = retrofit.gson.fromJson(response.body().toString(), Integer[].class);
                temp = Arrays.asList(array);
                for(Integer obj : temp){
                    for(Sauna sauna: getAllSaunas().getValue()) {
                        if (obj.intValue() == sauna.getId()) {
                            notifiedSaunas.add(sauna);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
        return notifiedSaunas;
    }
    //-------------Sauna-------------------------------------------------------------------------------------

    public void saunaInsert(Sauna sauna){
       // MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.insert(sauna);
        //});
    }

    //delete all saunas
    public void emptySaunaRepo(){
        //MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.deleteAll();
        //});
    }

    //return all saunas
    public LiveData<List<Sauna>> getAllSaunas(){
        return saunasDao.getAllSaunas();
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
