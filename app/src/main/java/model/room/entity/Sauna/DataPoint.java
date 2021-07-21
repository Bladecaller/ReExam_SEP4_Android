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
    private int CO2iD;
    private int humidityiD;
    private int temperatureiD;

    public DataPoint(@NonNull int id,int saunaId, int CO2iD, int humidityiD, int temperatureiD){
        this.id = id;
        this.saunaId = saunaId;
        this.CO2iD = CO2iD;
        this.humidityiD = humidityiD;
        this.temperatureiD = temperatureiD;
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

    public int getCO2iD() {
        return CO2iD;
    }

    public void setCO2iD(int CO2iD) {
        this.CO2iD = CO2iD;
    }

    public int getHumidityiD() {
        return humidityiD;
    }

    public void setHumidityiD(int humidityiD) {
        this.humidityiD = humidityiD;
    }

    public int getTemperatureiD() {
        return temperatureiD;
    }

    public void setTemperatureiD(int temperatureiD) {
        this.temperatureiD = temperatureiD;
    }

    public int getSaunaId() {
        return saunaId;
    }

    public void setSaunaId(int saunaId) {
        this.saunaId = saunaId;
    }
}
