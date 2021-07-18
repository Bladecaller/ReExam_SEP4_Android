package Model.Account;

import Model.Sauna.Sauna;

import java.util.List;

public abstract class Account {
    public int userID;
    public String username;
    public String password;
    public RightsEnum rights;
    public List<Sauna> saunas;
    public String establishmentName;

    public Account(int ID, String username, String password,RightsEnum rights, List<Sauna> saunas, String establishment){
    this.userID = ID;
    this.username = username;
    this.password = password;
    this.rights = rights;
    this.saunas = saunas;
    this.establishmentName = establishment;
    }

    public RightsEnum getRights() {
        return rights;
    }
}
