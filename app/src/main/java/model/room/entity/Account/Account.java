package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import model.room.entity.Sauna.Sauna;

import java.util.List;
@Entity
public abstract class Account {
    @PrimaryKey
    @NonNull
    private int userID;
    private String username;
    private String password;

    private RightsEnum rights;
    private List<Sauna> saunas;
    private String establishmentName;

    public Account(@NonNull int ID, String username, String password,RightsEnum rights, List<Sauna> saunas, String establishment){
        this.userID = ID;
        this.username = username;
        this.password = password;
        this.rights = rights;
        this.saunas = saunas;
        this.establishmentName = establishment;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRights(RightsEnum rights) {
        this.rights = rights;
    }

    public List<Sauna> getSaunas() {
        return saunas;
    }

    public void setSaunas(List<Sauna> saunas) {
        this.saunas = saunas;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public RightsEnum getRights() {
        return rights;
    }
}
