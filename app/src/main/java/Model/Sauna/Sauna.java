package Model.Sauna;

import Model.Sauna.DataPoint;

import java.util.Date;
import java.util.List;

public class Sauna {
    public int saunaID;
    public int reservedForRoomNumber;
    public Date reservedTimeFrom;
    public Date reservedTimeTo;
    public boolean reserved;
    public List<DataPoint> data;
    public float humidityThreshold;
    public float CO2Threshold;
    public float temperatureThreshold;
    public Servo servo;

    public Sauna(int ID,int roomNum, Date from, Date to, boolean reserved, List<DataPoint> list, float humTH, float CO2TH, float tempTH, Servo servo){
        this.saunaID = ID;
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
                Values.add(data.get(i).CO2);
            }
        return Values;
    }

    public List<Humidity> getHumidity(){
        List<Humidity> Values = null;
        for(int i = 0; i <= data.size(); i++){
            Values.add(data.get(i).humidity);
        }
        return Values;
    }

    public List<Temperature> getTemperature(){
        List<Temperature> Values = null;
        for(int i = 0; i <= data.size(); i++){
            Values.add(data.get(i).temperature);
        }
        return Values;
    }

    public void notifyDanger(){
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).CO2.getValue() >= this.CO2Threshold){
                System.out.println("CO2 is too high for sauna " + saunaID);
                break;
            }
        }
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).humidity.getValue() >= humidityThreshold){
                System.out.println("Humidity is too high for sauna " + saunaID);
                break;
            }
        }
        for(int i = 0; i <= data.size(); i++){
            if(data.get(i).temperature.getValue() >= temperatureThreshold){
                System.out.println("Temperature is too high for sauna " + saunaID);
                break;
            }
        }
    }


}
