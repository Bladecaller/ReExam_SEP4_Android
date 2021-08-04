package api;



import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InterfaceAPI {

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

    @POST("APIReservations")
    Call<Void> createReservation(
            @Body Reservation reservation
    );

    @POST("APIUsers")
    Call<Void> createNewBusinessOwnerAccount(
            @Body BusinessOwner account
    );

    @POST("APIUsers")
    Call<Void> createNewEmployeeAccount(
            @Body Employee account
    );

    @POST("APIUsers")
    Call<Void> createNewCustomerAccount(
            @Body Customer account

    );

    @DELETE("APIUsers/{id}")
    Call<Account> removeUser(
            @Path(value = "id", encoded = true) int id
    );

    @PUT("APIUsers/{id}")
    Call<Void> setRightsOfACustomer(
            @Path(value = "id", encoded = true) int id,
            @Body Customer account
    );

    @PUT("APIUsers/{id}")
    Call<Void> setRightsOfAnEmployee(
            @Path(value = "id", encoded = true) int id,
            @Body Employee account
    );

    @PUT("APIUsers/{id}")
    Call<Void> setRightsOfABusinessOwner(
            @Path(value = "id", encoded = true) int id,
            @Body BusinessOwner account
    );

    @PUT("APISaunas/{id}")
    Call<Void> setThresholds(
            @Path(value = "id", encoded = true) int id,
            @Body Sauna sauna
    );

    @GET("APINotificationHistories")
    Call<List<Integer>>  checkNotification();

    @GET("hardwarecontroller/getopenthedoor/{id}")
    Call<Void> openDoor(
            @Path(value = "id", encoded = true) int id
    );

}
