package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CO2 {
    @PrimaryKey
    @NonNull
    private int id;
    private float value;
    private UnitEnum unit;

    public CO2(@NonNull int id, float value){
        this.id = id;
        this.value = value;
        this.unit = UnitEnum.PPM;
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
