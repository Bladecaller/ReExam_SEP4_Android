package com.example.sep4_android.Model.Account;

import com.example.sep4_android.Model.Sauna.Sauna;

import java.util.Date;

public class Reservation {
    public int roomNumber;
    public Sauna sauna;
    public Date bookTimeFrom;
    public Date bookTimeTo;

    public Reservation(int roomNumber, Sauna sauna, Date from, Date to){
        this.roomNumber = roomNumber;
        this.sauna = sauna;
        this.bookTimeFrom = from;
        this.bookTimeTo = to;
    }
}
