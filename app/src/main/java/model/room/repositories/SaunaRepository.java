package model.room.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.SaunasDao;
import model.room.entity.IntegerEntity;
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
                for(Sauna obj : response.body()){
                    saunaInsert(obj);
                    Log.d("RESPONSE API",Float.toString(obj.getCO2Threshold()));
                }


            }

            @Override
            public void onFailure(Call<List<Sauna>> call, Throwable t) {
                System.out.println("Failed at populateSaunasRepo");

            }

        });
    }

    public void openDoorAPI(int id){
        Call call = retrofit.api.openDoor(id);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS AT OPEN DOOR ?: " + response.message());

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at OpenDoor");
            }
        });
    }

    public void checkNotificationsAPI(){
        Call<List<Integer>> call = retrofit.api.checkNotification();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                System.out.println("SUCCESS AT NOTIFICATIONS CHECK ?: " + response.message());
                emptyIntegerRepo();
                for(Integer intObj: response.body()){
                    IntegerEntity ent = new IntegerEntity(intObj+1,intObj);
                    saunaIdInsert(ent);
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });
    }
    public void setThresholdsAPI(float CO2, float humidity, float temperature,Sauna sauna){
        sauna.setCO2Threshold(CO2);
        sauna.setHumidityThreshold(humidity);
        sauna.setTemperatureThreshold(temperature);
        Call call = retrofit.api.setThresholds(sauna.getSaunaID(), sauna);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("SUCCESS AT SET THRESHOLDS? :" + response.message());
                emptyAndPopulateSaunasRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setThresholds");
            }
        });
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
    //--------------IntegerEntity---------------------------------------------------------------------------------

    public void saunaIdInsert(IntegerEntity entity){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.insertSaunaID(entity);
        });
    }

    //delete all saunas
    public void emptyIntegerRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.deleteIntegersAll();
        });
    }

    //return all saunas
    public LiveData<List<IntegerEntity>> getAllIntegerEntities(){
        return saunasDao.getAllnotifiedSaunaIDs();
    }
}
