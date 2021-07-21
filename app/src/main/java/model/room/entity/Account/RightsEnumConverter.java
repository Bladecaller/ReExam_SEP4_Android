package model.room.entity.Account;

import androidx.room.TypeConverter;

public class RightsEnumConverter {

    @TypeConverter
    public static int fromRightsEnumToInt(RightsEnum r){
        return r.getValue();
    }

    @TypeConverter
    public static RightsEnum fromIntToRightsEnum(int i){
        return (RightsEnum.values()[i]);
    }
}
