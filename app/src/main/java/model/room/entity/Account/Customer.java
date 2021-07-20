package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;
import java.util.Date;

import model.room.entity.Sauna.Sauna;
@Entity
public class Customer extends Account {
    private int roomNumber;
    private List<Reservation> reservations;

    public Customer(@NonNull int roomNum, int ID, String username, String password, RightsEnum rights, List<Sauna> saunas, List<Reservation> reservations, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
        this.reservations = reservations;
        this.roomNumber = roomNum;
    }

    public void book(Sauna sauna, Date from, Date to){
        Reservation booking = new Reservation(this.roomNumber,this.getUserID(),sauna, from, to);
        reservations.add(booking);
        for(int i = 0; i<= getSaunas().size(); i++){
            if(sauna == getSaunas().get(i)){
                getSaunas().get(i).setReserved(true);
                getSaunas().get(i).setReservedForRoomNumber(this.roomNumber);
                getSaunas().get(i).setReservedTimeFrom(from);
                getSaunas().get(i).setReservedTimeTo(to);
            }
        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
