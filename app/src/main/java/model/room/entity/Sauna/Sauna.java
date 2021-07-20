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
    private Date reservedTimeFrom;
    private Date reservedTimeTo;
    private boolean reserved;
    private List<DataPoint> data;
    private float humidityThreshold;
    private float CO2Threshold;
    private float temperatureThreshold;
    private Servo servo;

    public Sauna(@NonNull int ID,int roomNum, Date from, Date to, boolean reserved, List<DataPoint> list, float humTH, float CO2TH, float tempTH, Servo servo){
        this.id = ID;
        this.reservedForRoomNumber = roomNum;
        this.reservedTimeFrom = from;
        this.reservedTimeTo = to;
        this.reserved = reserved;
        this.data = list;
        this.humidityThreshold = humTH;
        this.CO2Threshold = CO2TH;
        this.temperatureThreshold = tempTH;
        this.servo = servo;
    }

    public List<CO2> getCO2(){
        List<CO2> Values = null;
            for(int i = 0; i <= data.size(); i++){
                Values.add(data.get(i).getCO2());
            }
        return Values;
    }

    public List<Humidity> getHumidity(){
        List<Humidity> Values = null;
        for(int i = 0; i <= data.size(); i++){
            Values.add(data.get(i).getHumidity());
        }
        return Values;
    }

    public List<Temperature> getTemperature(){
        List<Temperature> Values = null;
        for(int i = 0; i <= data.size(); i++){
            Values.add(data.get(i).getTemperature());
        }
        return Values;
    }

    public void notifyDanger(){
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).getCO2().getValue() >= this.CO2Threshold){
                System.out.println("CO2 is too high for sauna " + getId());
                break;
            }
        }
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).getHumidity().getValue() >= humidityThreshold){
                System.out.println("Humidity is too high for sauna " + getId());
                break;
            }
        }
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).getTemperature().getValue() >= temperatureThreshold){
                System.out.println("Temperature is too high for sauna " + getId());
                break;
            }
        }
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

    public Date getReservedTimeFrom() {
        return reservedTimeFrom;
    }

    public void setReservedTimeFrom(Date reservedTimeFrom) {
        this.reservedTimeFrom = reservedTimeFrom;
    }

    public Date getReservedTimeTo() {
        return reservedTimeTo;
    }

    public void setReservedTimeTo(Date reservedTimeTo) {
        this.reservedTimeTo = reservedTimeTo;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public List<DataPoint> getData() {
        return data;
    }

    public void setData(List<DataPoint> data) {
        this.data = data;
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

    public Servo getServo() {
        return servo;
    }

    public void setServo(Servo servo) {
        this.servo = servo;
    }
}
