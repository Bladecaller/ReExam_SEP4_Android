package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class DataPoint {
    @PrimaryKey
    @NonNull
    private int id;
    private int saunaId;
    private Date time;
    private CO2 CO2;
    private Humidity humidity;
    private Temperature temperature;

    public DataPoint(@NonNull int id,int saunaId, CO2 co2, Humidity hum, Temperature temp){
        this.id = id;
        this.saunaId = saunaId;
        this.CO2 = co2;
        this.humidity = hum;
        this.temperature = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public model.room.entity.Sauna.CO2 getCO2() {
        return CO2;
    }

    public void setCO2(model.room.entity.Sauna.CO2 CO2) {
        this.CO2 = CO2;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public int getSaunaId() {
        return saunaId;
    }

    public void setSaunaId(int saunaId) {
        this.saunaId = saunaId;
    }
}
