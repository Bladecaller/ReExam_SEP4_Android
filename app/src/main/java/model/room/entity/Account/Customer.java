package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.List;
import java.util.Date;

import model.room.entity.Sauna.Sauna;
@Entity
public class Customer extends Account {
    @ColumnInfo(name = "roomNum")
    private int roomNumber;

    public Customer(@NonNull int UserID, String Username, String Password,String Rights) {
        super(UserID, Username, Password, Rights);
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
