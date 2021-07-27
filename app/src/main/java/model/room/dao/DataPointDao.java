package model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Customer;
import model.room.entity.Sauna.DataPoint;
@Dao
public interface DataPointDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DataPoint dataPoint);

    @Query("DELETE FROM DataPoint")
    void deleteAll();

    @Query("SELECT * FROM DataPoint")
    LiveData<List<DataPoint>> getAllDataPoints();

}
