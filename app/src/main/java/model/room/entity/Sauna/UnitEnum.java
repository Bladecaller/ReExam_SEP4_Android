package model.room.entity.Sauna;

public enum UnitEnum {
    CENTIGRADE(0),
    PPM(1),
    PERCENT(2);


    public final int value;

    UnitEnum(int value){
        this.value = value;
    }
    public int getValue(){
        return  value;
    }
}
