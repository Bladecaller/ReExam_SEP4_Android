package model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.RightsEnum;
@Dao
public interface AccountsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Query("DELETE FROM Account")
    void deleteAll();

    @Query("SELECT * FROM Customer ")
    LiveData<List<Customer>> getAllCustomers();

    @Query("SELECT * FROM Employee ")
    LiveData<List<Employee>> getAllEmployees();

    @Query("SELECT * FROM BusinessOwner ")
    LiveData<List<BusinessOwner>> getAllBusinessOwners();
}
