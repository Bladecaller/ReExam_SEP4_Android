package Model.Sauna;

public class Servo {
    public boolean spun;

    public Servo(){
        spun = false;
    }

    public void spin(){
        if(spun){
            spun = false;
        }else{
            spun = true;
        }
    }

    public boolean getStatus(){
        return spun;
    }
}
