package model.room.dao;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;

public interface SaunasDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sauna saunas);

    @Query("DELETE FROM Sauna")
    void deleteAll();

    @Query("SELECT * FROM Sauna ")
    LiveData<List<Sauna>> getAllSaunas();

}
