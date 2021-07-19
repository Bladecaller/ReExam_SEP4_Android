package model.Sauna;

public class CO2 {
    private float value;
    public UnitEnum unit;

    public CO2(float value){
        this.value = value;
        this.unit = UnitEnum.PPM;
    }

    public float getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }
}
