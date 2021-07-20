package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;

import model.room.entity.Sauna.Sauna;
@Entity
public class BusinessOwner extends Account{

    public List<Account> accounts;

    public BusinessOwner(@NonNull int ID, String username, String password, RightsEnum rights, List<Sauna> saunas, List<Account> accounts, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
    }

    public void setRights(RightsEnum rights, Account acc, int roomNumber){
        for (int i = 0; i <= accounts.size();i ++){
            if(acc == accounts.get(i)){
                if(rights == RightsEnum.Employee){
                    Employee employee = new Employee(accounts.get(i).getUserID(), accounts.get(i).getUsername(), accounts.get(i).getPassword(), rights, accounts.get(i).getSaunas(), accounts, getEstablishmentName());
                    accounts.remove(accounts.get(i));
                    accounts.add(employee);
                }

                if(rights == RightsEnum.Customer){
                    Customer customer = new Customer(roomNumber, accounts.get(i).getUserID(), accounts.get(i).getUsername(), accounts.get(i).getPassword(), rights, accounts.get(i).getSaunas(), null, getEstablishmentName());
                    accounts.remove(accounts.get(i));
                    accounts.add(customer);
                }
            }
        }
    }

    public void addUser(int ID, String username, String password, RightsEnum rights, List<Sauna> saunas,int roomNumber,List<Reservation> reservations){
        Account acc;
        if (rights == RightsEnum.Employee){
            acc = new Employee(ID, username, password, rights, saunas, accounts, getEstablishmentName());
        }else if(rights ==RightsEnum.Customer){
            acc = new Customer(roomNumber, ID, username, password, rights, saunas, reservations, getEstablishmentName());
        }

    }

    public void removeUser(int userID){
        for (int i = 0; i <= accounts.size();i ++){
            if(userID == accounts.get(i).getUserID()){
                accounts.remove(accounts.get(i));
            }
        }
    }

    public void setThresholds(float temp, float hum, float CO2){
        for (int i = 0; i <= getSaunas().size();i ++) {
            getSaunas().get(i).humidityThreshold = hum;
            getSaunas().get(i).temperatureThreshold = temp;
            getSaunas().get(i).CO2Threshold = CO2;
        }
    }
}
