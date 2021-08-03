package model.room.entity;

import android.provider.ContactsContract;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;

public class Converters {
    @TypeConverter
    public static List<DataPoint> fromString(String value) {
        Type listType = new TypeToken<List<DataPoint>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<DataPoint> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static RightsEnum fromStringToRights(String value){
        Type enumType = new TypeToken<RightsEnum>() {}.getType();
        return new Gson().fromJson(value, enumType);
    }
    @TypeConverter
    public static String fromRightsToString(RightsEnum enumm){
        Gson gson = new Gson();
        String json = gson.toJson(enumm);
        return json;
    }
}
