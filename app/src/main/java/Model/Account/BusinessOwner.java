package Model.Account;

import java.util.List;

import Model.Sauna.Sauna;

public class BusinessOwner extends Account{

    public List<Account> accounts;

    public BusinessOwner(int ID, String username, String password, RightsEnum rights, List<Sauna> saunas, List<Account> accounts, String establishment) {
        super(ID, username, password, rights, saunas, establishment);
    }

    public void setRights(RightsEnum rights, Account acc, int roomNumber){
        for (int i = 0; i <= accounts.size();i ++){
            if(acc == accounts.get(i)){
                if(rights == RightsEnum.Employee){
                    Employee employee = new Employee(accounts.get(i).userID, accounts.get(i).username, accounts.get(i).password, rights, accounts.get(i).saunas, accounts, establishmentName);
                    accounts.remove(accounts.get(i));
                    accounts.add(employee);
                }

                if(rights == RightsEnum.Customer){
                    Customer customer = new Customer(roomNumber, accounts.get(i).userID, accounts.get(i).username, accounts.get(i).password, rights, accounts.get(i).saunas, null, establishmentName);
                    accounts.remove(accounts.get(i));
                    accounts.add(customer);
                }
            }
        }
    }

    public void addUser(int ID, String username, String password, RightsEnum rights, List<Sauna> saunas,int roomNumber,List<Reservation> reservations){
        Account acc;
        if (rights == RightsEnum.Employee){
            acc = new Employee(ID, username, password, rights, saunas, accounts, establishmentName);
        }else if(rights ==RightsEnum.Customer){
            acc = new Customer(roomNumber, ID, username, password, rights, saunas, reservations, establishmentName);
        }

    }

    public void removeUser(int userID){
        for (int i = 0; i <= accounts.size();i ++){
            if(userID == accounts.get(i).userID){
                accounts.remove(accounts.get(i));
            }
        }
    }

    public void setThresholds(float temp, float hum, float CO2){
        for (int i = 0; i <= saunas.size();i ++) {
            saunas.get(i).humidityThreshold = hum;
            saunas.get(i).temperatureThreshold = temp;
            saunas.get(i).CO2Threshold = CO2;
        }
    }
}
