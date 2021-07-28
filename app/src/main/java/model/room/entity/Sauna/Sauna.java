package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;
@Entity
public class Sauna {
    @PrimaryKey
    @NonNull
    private int id;
    private int reservedForRoomNumber;
    private String reservedTimeFrom;
    private String reservedTimeTo;
    private boolean reserved;
    private float humidityThreshold;
    private float CO2Threshold;
    private float temperatureThreshold;

    public Sauna(@NonNull int id,int reservedForRoomNumber, String reservedTimeFrom, String reservedTimeTo, boolean reserved, float humidityThreshold, float CO2Threshold, float temperatureThreshold){
        this.id = id;
        this.reservedForRoomNumber = reservedForRoomNumber;
        this.reservedTimeFrom = reservedTimeFrom;
        this.reservedTimeTo = reservedTimeTo;
        this.reserved = reserved;
        this.humidityThreshold = humidityThreshold;
        this.CO2Threshold = CO2Threshold;
        this.temperatureThreshold = temperatureThreshold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservedForRoomNumber() {
        return reservedForRoomNumber;
    }

    public void setReservedForRoomNumber(int reservedForRoomNumber) {
        this.reservedForRoomNumber = reservedForRoomNumber;
    }

    public String getReservedTimeFrom() {
        return reservedTimeFrom;
    }

    public void setReservedTimeFrom(String reservedTimeFrom) {
        this.reservedTimeFrom = reservedTimeFrom;
    }

    public String getReservedTimeTo() {
        return reservedTimeTo;
    }

    public void setReservedTimeTo(String reservedTimeTo) {
        this.reservedTimeTo = reservedTimeTo;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public float getHumidityThreshold() {
        return humidityThreshold;
    }

    public void setHumidityThreshold(float humidityThreshold) {
        this.humidityThreshold = humidityThreshold;
    }

    public float getCO2Threshold() {
        return CO2Threshold;
    }

    public void setCO2Threshold(float CO2Threshold) {
        this.CO2Threshold = CO2Threshold;
    }

    public float getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public void setTemperatureThreshold(float temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }
}
