package api;



import java.util.Date;
import java.util.List;

import model.Account.Account;
import model.Account.Reservation;
import model.Account.RightsEnum;
import model.Sauna.Sauna;
import model.Sauna.Servo;
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

    @POST("login")
    Call<Account> logIn(
            @Query("username") String username,
            @Query("password") String password
    );

    @POST("openDoor")
    Call<Servo> openTheDoor(
            @Query("saunaID") int saunaID
    );

    @POST("createReservation")
    Call<Reservation> createReservation(
            @Query("saunaID") int sauinaID,
            @Query("roomNumber") String roomNum,
            @Query("from")Date timeFrom,
            @Query("to") Date timeTo
    );

    @POST("createAccount")
    Call<Account> createNewAccount(
            @Query("username") String username,
            @Query("password") String password,
            @Query("rights") RightsEnum rights,
            @Query("saunas") List<Sauna> saunas
    );

    @POST("retrofit")
    Call<Account> removeUser(
            @Query("userID") int userID
    );

    @POST("setRights")
    Call<Account> setRights(
          @Query("rights") RightsEnum rights,
          @Query("userID") int userID
    );

    @POST("setThresholds")
    Call<List<Sauna>> setThresholds(
            @Query("temperature") float temperature,
            @Query("humidity") float humidity,
            @Query("CO2") float CO2
    );
}
