package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Servo {
    @PrimaryKey
    @NonNull
    private int id;
    private boolean spun;

    public Servo(int id){
        this.id = id;
        spun = false;
    }

    public void spin(){
        if(spun){
            spun = false;
        }else{
            spun = true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSpun() {
        return spun;
    }

    public void setSpun(boolean spun) {
        this.spun = spun;
    }
}
