package model.room.entity.Account;

public enum RightsEnum {
    Customer(0),
    Employee(1),
    BusinessOwner(2);

    public final int value;

    RightsEnum(int value){
        this.value = value;
    }
    public int getValue(){
        return  value;
    }

}
