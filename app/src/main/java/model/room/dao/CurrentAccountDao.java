package model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.CurrentAccount;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
@Dao
public interface CurrentAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(CurrentAccount account);

    @Query("DELETE FROM CurrentAccount")
    void deleteAll();

    @Query("SELECT * FROM CurrentAccount")
    LiveData<List<CurrentAccount>> getCurrentAccountList();
}
