package model.room.entity.Account;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.util.List;

import model.room.entity.Sauna.Sauna;
@Entity
public class BusinessOwner extends Account{

    public BusinessOwner(@NonNull int UserID, String Username, String Password,String Rights) {
        super(UserID, Username, Password, Rights);
    }

}
