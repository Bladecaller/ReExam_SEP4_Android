package model.room.entity.Account;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import model.room.entity.Sauna.Sauna;
@Entity
public class Reservation {
    @PrimaryKey
    @NonNull
    private int id;
    private int customerId;
    private int saunaId;
    private int roomNumber;
    private String bookTimeFrom;
    private String bookTimeTo;

    public Reservation(@NonNull int id,int roomNumber,int customerId,int saunaId, String bookTimeFrom, String bookTimeTo){
        this.id = id;
        this.roomNumber = roomNumber;
        this.saunaId = saunaId;
        this.customerId = customerId;
        this.bookTimeFrom = bookTimeFrom;
        this.bookTimeTo = bookTimeTo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSaunaId() {
        return saunaId;
    }

    public void setSaunaId(int saunaId) {
        this.saunaId = saunaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    public String getBookTimeFrom() {
        return bookTimeFrom;
    }

    public void setBookTimeFrom(String bookTimeFrom) {
        this.bookTimeFrom = bookTimeFrom;
    }

    public String getBookTimeTo() {
        return bookTimeTo;
    }

    public void setBookTimeTo(String bookTimeTo) {
        this.bookTimeTo = bookTimeTo;
    }

}
