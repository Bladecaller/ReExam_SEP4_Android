package model.Account;

import java.util.List;
import java.util.Date;

import model.Sauna.Sauna;

public class Customer extends Account {
    public int roomNumber;
    public List<Reservation> reservations;

    public Customer(int roomNum, int ID, String username, String password, RightsEnum rights, List<Sauna> saunas, List<Reservation> reservations, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
        this.reservations = reservations;
        this.roomNumber = roomNum;
    }

    public void book(Sauna sauna, Date from, Date to){
        Reservation booking = new Reservation(this.roomNumber,sauna, from, to);
        reservations.add(booking);
        for(int i = 0; i<= saunas.size(); i++){
            if(sauna == saunas.get(i)){
                saunas.get(i).reserved=true;
                saunas.get(i).reservedForRoomNumber = this.roomNumber;
                saunas.get(i).reservedTimeFrom = from;
                saunas.get(i).reservedTimeTo = to;
            }
        }
    }
}
