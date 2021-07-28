package model.room.repositories;

import android.app.Application;
import android.os.PowerManager;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public SaunaRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        saunasDao = db.saunaDao();
    }

    public void emptyAndPopulateSaunasRepoAPI(){
        Call<List<Sauna>> call = retrofit.api.getAllSaunas();
        call.enqueue(new Callback<List<Sauna>>(){
            @Override
            public void onResponse (Call <List<Sauna>> call, Response<List<Sauna>> response){
                System.out.println("SUCCESS " + response.body());
                emptySaunaRepo();
                List<Sauna> temp = response.body();
                for(Sauna obj : temp){
                    saunaInsert(obj);
                    Log.d("RESPONSE API",obj.getReservedTimeTo());
                }


            }

            @Override
            public void onFailure(Call<List<Sauna>> call, Throwable t) {
                System.out.println("Failed at populateSaunasRepo");

            }

        });
    }

    public String openDoorAPI(Sauna sauna){
        final String[] temp = {null};
        Call <String> call = retrofit.api.openDoor(sauna.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                temp[0] = response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                temp[0] = "Failed opening door";
                System.out.println("Failed at OpenDoor");
            }
        });
        return temp[0];
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
       MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.insert(sauna);
        });
    }

    //delete all saunas
    public void emptySaunaRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.deleteAll();
        });
    }

    //return all saunas
    public LiveData<List<Sauna>> getAllSaunas(){
        return saunasDao.getAllSaunas();
    }
}
