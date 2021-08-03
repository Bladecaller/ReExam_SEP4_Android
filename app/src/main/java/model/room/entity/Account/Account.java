package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public abstract class Account {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "UserID")
    private int UserID;

    @ColumnInfo(name = "Username")
    private String Username;

    @ColumnInfo(name = "Password")
    private String Password;

    @ColumnInfo(name = "Rights")
    private String Rights;

    public Account(@NonNull int UserID, String Username, String Password,String Rights){
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.Rights = Rights;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRights(String Rights) {
        this.Rights = Rights;
    }

    public String getRights() {
        return Rights;
    }
}
