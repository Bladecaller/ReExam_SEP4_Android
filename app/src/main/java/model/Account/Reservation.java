package model.Account;



import java.util.Date;

import model.Sauna.Sauna;

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
