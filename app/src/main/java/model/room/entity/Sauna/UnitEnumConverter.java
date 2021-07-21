package model.room.entity.Sauna;

import androidx.room.TypeConverter;

import model.room.entity.Account.RightsEnum;

public class UnitEnumConverter {

    @TypeConverter
    public static int fromUnitEnumToInt(UnitEnum r){
        return r.getValue();
    }

    @TypeConverter
    public static UnitEnum fromIntToUnitEnum(int i){
        return (UnitEnum.values()[i]);
    }
}