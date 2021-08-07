package model.room.dao;

import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.lang.Object.*;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;
import model.room.entity.IntegerEntity;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
@Dao
public interface SaunasDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sauna saunas);

    @Query("DELETE FROM Sauna")
    void deleteAll();

    @Query("SELECT * FROM Sauna ")
    LiveData<List<Sauna>> getAllSaunas();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSaunaID(IntegerEntity saunaID);

    @Query("DELETE FROM IntegerEntity")
    void deleteIntegersAll();

    @Query("SELECT * FROM IntegerEntity ")
    LiveData<List<IntegerEntity>> getAllnotifiedSaunaIDs();



}
