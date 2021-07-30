package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;
import java.util.Date;

import model.room.entity.Sauna.Sauna;
@Entity
public class Customer extends Account {
    private int roomNumber;

    public Customer(@NonNull int userID, String username, String password, int rights, String establishmentName, int roomNumber) {
        super(userID, username, password, rights, establishmentName);
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
