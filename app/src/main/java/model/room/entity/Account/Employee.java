package model.room.entity.Account;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;
import java.util.List;

import model.room.entity.Sauna.Sauna;
@Entity
public class Employee extends Account {
    public boolean notifications = false;
    public List<Customer> customers;

    public Employee(@NonNull int ID, String username, String password, RightsEnum rights, List<Sauna> saunas, List<Account> accounts, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
        for(int i = 0; i<= accounts.size(); i++){
            if(accounts.get(i) instanceof Customer){
                customers.add((Customer)accounts.get(i));
            }
        }

    }

    public void book(int roomNumber, Sauna sauna, Date from, Date to){
        int tempId = 9999;
        for (Customer cust : customers){
            if (cust.getRoomNumber() == roomNumber){
                tempId = cust.getUserID();
            }
        }
        Reservation booking = new Reservation(roomNumber, tempId,sauna, from, to);

        for(int i = 0; i<= customers.size(); i++){
            if(roomNumber == customers.get(i).getRoomNumber()){
                customers.get(i).getReservations().add(booking);
            }
        }

        for(int i = 0; i<= getSaunas().size(); i++){
            if(sauna == getSaunas().get(i)){
                getSaunas().get(i).setReserved(true);
                getSaunas().get(i).setReservedForRoomNumber(roomNumber);
                getSaunas().get(i).setReservedTimeFrom(from);
                getSaunas().get(i).setReservedTimeTo(to);
            }
        }
    }

    public void openDoor(Sauna sauna){
        sauna.getServo().spin();
    }
}
