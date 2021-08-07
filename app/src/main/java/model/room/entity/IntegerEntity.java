package model.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class IntegerEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private int saunaID;
    public IntegerEntity(int id, int saunaID){
        this.id=id;
        this.saunaID=saunaID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaunaID() {
        return saunaID;
    }

    public void setSaunaID(int saunaID) {
        this.saunaID = saunaID;
    }
}
