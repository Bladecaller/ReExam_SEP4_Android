package com.example.sep4_android.Model.Account;

import com.example.sep4_android.Model.Sauna.Sauna;

import java.util.Date;

public class Reservation {
    public int roomNumber;
    public Sauna sauna;
    public Date time;

    public Reservation(int roomNumber, Sauna sauna, Date time){
        this.roomNumber = roomNumber;
        this.sauna = sauna;
        this.time = time;
    }
}
