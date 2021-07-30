package model.room.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.AccountsDao;
import model.room.dao.DataPointDao;
import model.room.entity.Account.Customer;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPointRepository {
    private MyRetrofit retrofit;
    public DataPointDao dataPointDao;

    public DataPointRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        dataPointDao = db.dataPointDao();
    }

    public void emptyAndPopulateDatapointRepoAPI(int saunaId){
        Call<List<DataPoint>> call = retrofit.api.getAllDataPoints(saunaId);
        call.enqueue(new Callback<List<DataPoint>>(){
            @Override
            public void onResponse (Call <List<DataPoint>> call, Response<List<DataPoint>> response){
                System.out.println("SUCCESS " + response.body());
                emptyDataRepo();
                List<DataPoint> temp;
                temp = response.body();
                for(DataPoint obj : temp){
                    datapointInsert(obj);
                    Log.d("RESPONSE API",obj.getTime());
                }
            }

            @Override
            public void onFailure(Call<List<DataPoint>> call, Throwable t) {
                System.out.println("Failed at populateDatapointRepo");
            }

        });
    }

    //------------Datapoint---------------------------------------------------------------------------------

    //store a single datapoint
    public void datapointInsert(DataPoint dataPoint){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataPointDao.insert(dataPoint);
        });
    }

    //delete all datapoints
    public void emptyDataRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataPointDao.deleteAll();
        });
    }

    // return all datapoints for a specific sauna
    public LiveData<List<DataPoint>> getAllDataPoints(){
        return dataPointDao.getAllDataPoints();
    }

}
