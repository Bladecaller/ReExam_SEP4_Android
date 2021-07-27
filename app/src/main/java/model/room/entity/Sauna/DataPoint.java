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
    private String time;
    private int CO2;
    private int humidity;
    private int temperature;

    public DataPoint(@NonNull int id,int saunaId, int CO2, int humidity, int temperature){
        this.id = id;
        this.saunaId = saunaId;
        this.CO2 = CO2;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidityiD(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperatureiD) {
        this.temperature = temperature;
    }

    public int getSaunaId() {
        return saunaId;
    }

    public void setSaunaId(int saunaId) {
        this.saunaId = saunaId;
    }
}
