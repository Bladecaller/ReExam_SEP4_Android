package api;



import java.util.Date;
import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.entity.Sauna.Servo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @POST("post")
    Call<String> post(@Query("username") String name);

    @GET("allAccounts")
    Call<List<Account>> getAllAccounts();

    @GET("allSaunas")
    Call<List<Sauna>> getAllSaunas();

    @GET("allEeservations")
    Call<List<Reservation>> getAllReservations();

    @GET("allDatapoints")
    Call<List<DataPoint>> getAllDataPoints(
            @Query("saunaId") int saunaId
    );

    @GET("login")
    Call<Account> logIn(
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("openDoor")
    Call openTheDoor(
            @Query("saunaID") int saunaID
    );

    @POST("createReservation")
    Call createReservation(
            @Query("saunaID") int saunaID,
            @Query("customerId") int customerID,
            @Query("roomNumber") int roomNum,
            @Query("from")Date timeFrom,
            @Query("to") Date timeTo
    );

    @POST("createAccount")
    Call createNewAccount(
            @Query("username") String username,
            @Query("password") String password,
            @Query("rights") RightsEnum rights
    );

    @POST("removeUser")
    Call removeUser(
            @Query("userID") int userID
    );

    @POST("setRights")
    Call setRights(
          @Query("rights") RightsEnum rights,
          @Query("userID") int userID
    );

    @GET("setThresholds")
    Call setThresholds(
            @Query("temperature") float temperature,
            @Query("humidity") float humidity,
            @Query("CO2") float CO2
    );

    @GET("notification")
    Call<Sauna> checkNotification();

}
