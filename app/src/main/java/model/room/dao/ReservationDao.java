package model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Reservation;
import model.room.entity.Sauna.Sauna;
@Dao
public interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertReservation(Reservation reservation);

    @Query("DELETE FROM Reservation")
    void deleteAll();

    @Query("SELECT * FROM Reservation WHERE UserID = (:customerId)")
    LiveData<List<Reservation>> getReservationsByCustomerId(int customerId);


}
