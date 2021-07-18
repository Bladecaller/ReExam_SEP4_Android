package Model.Sauna;

import java.util.Date;

public class DataPoint {
    public Date time;
    public CO2 CO2;
    public Humidity humidity;
    public Temperature temperature;

    public DataPoint(CO2 co2, Humidity hum, Temperature temp){
        this.CO2 = co2;
        this.humidity = hum;
        this.temperature = temp;
    }

}
