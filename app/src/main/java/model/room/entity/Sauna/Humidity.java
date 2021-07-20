package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Humidity {
    @PrimaryKey
    @NonNull
    private int id;
    private float value;
    private UnitEnum unit;

    public Humidity(@NonNull int id, float value){
        this.id = id;
        this.value = value;
        this.unit = UnitEnum.PERCENT;
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }
}
