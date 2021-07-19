package model.Sauna;

public class Temperature {
    private float value;
    public UnitEnum unit;

    public Temperature(float value){
        this.value = value;
        this.unit = UnitEnum.CENTIGRADE;
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }
}
