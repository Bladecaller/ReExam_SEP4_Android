package com.example.sep4_android.Model.Account;

import com.example.sep4_android.Model.Sauna.Sauna;

import java.util.Date;
import java.util.List;

public class Employee extends Account {
    public boolean notifications = false;
    public List<Customer> customers;

    public Employee(int ID, String username, String password, RightsEnum rights, List<Sauna> saunas,List<Account> accounts, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
        for(int i = 0; i<= accounts.size(); i++){
            if(accounts.get(i) instanceof Customer){
                customers.add((Customer)accounts.get(i));
            }
        }

    }

    public void book(int roomNumber, Sauna sauna, Date from, Date to){
        Reservation booking = new Reservation(roomNumber,sauna, from, to);

        for(int i = 0; i<= customers.size(); i++){
            if(roomNumber == customers.get(i).roomNumber){
                customers.get(i).reservations.add(booking);
            }
        }

        for(int i = 0; i<= saunas.size(); i++){
            if(sauna == saunas.get(i)){
                saunas.get(i).reserved=true;
                saunas.get(i).reservedForRoomNumber = roomNumber;
                saunas.get(i).reservedTimeFrom = from;
                saunas.get(i).reservedTimeTo = to;
            }
        }
    }

    public void openDoor(Sauna sauna){
        sauna.servo.spin();
    }
}
