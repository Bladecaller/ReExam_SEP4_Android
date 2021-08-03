package model.room.entity.Account;


import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.Date;
import java.util.List;

import model.room.entity.Sauna.Sauna;
@Entity
public class Employee extends Account {
    public boolean notifications = false;

    public Employee(@NonNull int UserID, String Username, String Password,String Rights) {
        super(UserID, Username, Password, Rights);
    }
}
