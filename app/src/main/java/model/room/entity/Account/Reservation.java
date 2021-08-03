package model.room.entity.Account;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"UserID","SaunaID","FromDateTime"})
public class Reservation {
    @NonNull
    @ColumnInfo(name = "UserID")
    private int UserID;
    @NonNull
    @ColumnInfo(name = "SaunaID")
    private int SaunaID;
    @NonNull
    @ColumnInfo(name = "FromDateTime")
    private String FromDateTime;
    @ColumnInfo(name = "ToDateTime")
    private String ToDateTime;

    public Reservation(@NonNull int UserID,@NonNull int SaunaID,@NonNull String FromDateTime, String ToDateTime){
        this.SaunaID = SaunaID;
        this.UserID = UserID;
        this.FromDateTime = FromDateTime;
        this.ToDateTime = ToDateTime;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public int getSaunaID() {
        return SaunaID;
    }

    public void setSaunaID(int saunaID) {
        this.SaunaID = saunaID;
    }

    public String getFromDateTime() {
        return FromDateTime;
    }

    public void setFromDateTime(String fromDateTime) {
        this.FromDateTime = fromDateTime;
    }

    public String getToDateTime() {
        return ToDateTime;
    }

    public void setToDateTime(String toDateTime) {
        this.ToDateTime = toDateTime;
    }

}
