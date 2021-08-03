package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Sauna {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "SaunaID")
    private int SaunaID;
    @ColumnInfo(name = "HumidityThreshold")
    private float humidityThreshold;
    @ColumnInfo(name = "CO2Threshold")
    private float CO2Threshold;
    @ColumnInfo(name = "TemperatureThreshold")
    private float temperatureThreshold;
    @ColumnInfo(name = "Datapoints")
    private List<DataPoint> Datapoints;

    public Sauna(@NonNull int SaunaID, float humidityThreshold, float CO2Threshold, float temperatureThreshold,List<DataPoint>  Datapoints){
        this.SaunaID = SaunaID;
        this.humidityThreshold = humidityThreshold;
        this.CO2Threshold = CO2Threshold;
        this.temperatureThreshold = temperatureThreshold;
        this.Datapoints = Datapoints;
    }

    public int getSaunaID() {
        return SaunaID;
    }

    public void setSaunaID(int saunaID) {
        this.SaunaID = saunaID;
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
    public List<DataPoint>  getDatapoints() {
        return Datapoints;
    }

    public void setDatapoints(List<DataPoint>  datapoints) {
        Datapoints = datapoints;
    }
}
