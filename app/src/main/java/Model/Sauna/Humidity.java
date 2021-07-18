package Model.Sauna;

public class Humidity {
    private float value;
    public UnitEnum unit;

    public Humidity(float value){
        this.value = value;
        this.unit = UnitEnum.PERCENT;
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }
}
