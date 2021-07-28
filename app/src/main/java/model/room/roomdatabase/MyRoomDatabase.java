package model.room.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.room.dao.AccountsDao;
import model.room.dao.DataPointDao;
import model.room.dao.ReservationDao;
import model.room.dao.SaunasDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnumConverter;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.entity.Sauna.UnitEnumConverter;

@Database(entities = {Account.class, BusinessOwner.class, Customer.class,
        Employee.class, Reservation.class, DataPoint.class, Sauna.class,
},
        version = 1, exportSchema = false)
@TypeConverters({RightsEnumConverter.class, UnitEnumConverter.class})
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract AccountsDao accountsDao();
    public abstract SaunasDao saunaDao();
    public abstract DataPointDao dataPointDao();
    public abstract ReservationDao reservationDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "my_room_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
