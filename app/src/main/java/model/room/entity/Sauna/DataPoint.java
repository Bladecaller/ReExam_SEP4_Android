package model.room.entity.Sauna;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataPoint {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "DatapointID")
    private int DatapointID;
    @ColumnInfo(name = "SaunaID")
    private int SaunaID;
    @ColumnInfo(name = "DateTime")
    private String DateTime;
    @ColumnInfo(name = "Temperature")
    private String Temperature;
    @ColumnInfo(name = "Co2")
    private String Co2;
    @ColumnInfo(name = "Humidity")
    private String Humidity;


    public DataPoint(@NonNull int DatapointID, int SaunaID, String DateTime, String Temperature, String Co2, String Humidity){
        this.DatapointID = DatapointID;
        this.SaunaID = SaunaID;
        this.DateTime = DateTime;
        this.Temperature = Temperature;
        this.Co2 = Co2;
        this.Humidity = Humidity;

    }

    public int getDatapointID() {
        return DatapointID;
    }

    public void setDatapointID(int datapointID) {
        this.DatapointID = datapointID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        this.DateTime = dateTime;
    }

    public String getCo2() {
        return Co2;
    }

    public void setCo2(String co2) {
        this.Co2 = co2;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        this.Humidity = humidity;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String Temperature) {
        this.Temperature = Temperature;
    }

    public int getSaunaID() {
        return SaunaID;
    }

    public void setSaunaID(int saunaID) {
        this.SaunaID = saunaID;
    }
}
