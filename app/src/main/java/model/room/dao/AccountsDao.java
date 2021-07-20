package model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;

public interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Query("DELETE FROM Account")
    void deleteAll();

    @Query("SELECT * FROM Account ")
    LiveData<List<Account>> getAllAccounts();
}
