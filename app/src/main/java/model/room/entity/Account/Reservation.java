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
    private Sauna sauna;
    private Date bookTimeFrom;
    private Date bookTimeTo;

    public Reservation(@NonNull int roomNumber,int customerId, Sauna sauna, Date from, Date to){
        this.roomNumber = roomNumber;
        this.saunaId = sauna.getId();
        this.customerId = customerId;
        this.sauna = sauna;
        this.bookTimeFrom = from;
        this.bookTimeTo = to;
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

    public Sauna getSauna() {
        return sauna;
    }

    public void setSauna(Sauna sauna) {
        this.sauna = sauna;
    }

    public Date getBookTimeFrom() {
        return bookTimeFrom;
    }

    public void setBookTimeFrom(Date bookTimeFrom) {
        this.bookTimeFrom = bookTimeFrom;
    }

    public Date getBookTimeTo() {
        return bookTimeTo;
    }

    public void setBookTimeTo(Date bookTimeTo) {
        this.bookTimeTo = bookTimeTo;
    }
}
