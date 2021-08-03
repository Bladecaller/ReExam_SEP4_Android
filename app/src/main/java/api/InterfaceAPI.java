package api;



import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @POST("post")
    Call<String> post(@Query("username") String name);

    @GET("APIUsers")
    Call<List<Account>> getAllAccounts();

    @GET("APISaunas")
    Call<List<Sauna>> getAllSaunas();

    @GET("APIReservations")
    Call<List<Reservation>> getAllReservations();

    @GET("APISauna/{id}")
    Call<Sauna> getAllDataPoints(
            @Path(value = "id", encoded = true) int id
    );

    @GET("apiuser/{username}/{password}")
    Call<Account> logIn(
            @Path(value = "username", encoded = true) String username,
            @Path(value = "password", encoded = true) String password
    );

    @POST("createReservation")
    Call createReservation(
            @Query("SaunaID") int saunaID,
            @Query("CustomerID") int customerID,
            @Query("FromDateTime")String timeFrom,
            @Query("ToDateTime") String timeTo
    );

    @POST("APIUsers")
    Call createNewAccount(
            @Query("Username") String username,
            @Query("Password") String password,
            @Query("Rights") String rights
    );

    @POST("APIUsers")
    Call<Void> createNewCustomerAccount(
            @Query("username") String username,
            @Query("password") String password,
            @Query("rights") String rights

    );

    @POST("removeUser")
    Call removeUser(
            @Query("userID") int userID
    );

    @POST("setRights")
    Call setRights(
          @Query("rights") String rights,
          @Query("userID") int userID
    );

    @GET("setThresholds")
    Call setThresholds(
            @Query("temperature") float temperature,
            @Query("humidity") float humidity,
            @Query("CO2") float CO2
    );

    @GET("notification")
    Call<List<Integer>>  checkNotification();

    @GET("openDoor")
    Call <String> openDoor(
            @Query("saunaId") int saunaId
    );

}
