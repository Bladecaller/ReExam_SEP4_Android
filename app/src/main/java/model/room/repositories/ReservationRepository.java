package model.room.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.ReservationDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationRepository {
    private MyRetrofit retrofit;
    private Account currentAccount;
    private ReservationDao reservationDao;

    public ReservationRepository(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        reservationDao = db.reservationDao();
        currentAccount = LoginRepository.getLoginRepositoryInstance().currentAccount;
    }

    public void emptyAndPopulateReservationRepoAPI(){
        Call<List<Reservation>> call = retrofit.api.getAllReservations();
        call.enqueue(new Callback<List<Reservation>>(){
            @Override
            public void onResponse (Call <List<Reservation>> call, Response<List<Reservation>> response){
                System.out.println("SUCCESS " + response.body());
                emptyReservationRepo();
                List<Reservation> temp;
                Reservation[] array = retrofit.gson.fromJson(response.body().toString(), Reservation[].class);
                temp = Arrays.asList(array);
                for(Reservation obj : temp){
                    reservationInsert(obj);
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                System.out.println("Failed at populateReservationRepo");
            }

        });
    }

    public void createReservationAPI(Reservation reservation){
        Call call = retrofit.api.createReservation(reservation.getCustomerId(),
                reservation.getSaunaId(),reservation.getRoomNumber(),
                reservation.getBookTimeFrom(), reservation.getBookTimeTo());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                emptyAndPopulateReservationRepoAPI();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at CreateReservation");
            }
        });
    }
    //--------Reservation--------------------------------------------------------------------------------------

    //store a single reservation
    public void reservationInsert(Reservation reservation){
        //MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.insertReservation(reservation);
        //});
    }

    //delete all reservations
    public void emptyReservationRepo(){
       // MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.deleteAll();
        //});
    }

    //return all reservations
    public LiveData<List<Reservation>> getAllReservations(){
        return reservationDao.getAllReservations();
    }

    // return all reservations for a specific customer
    public LiveData<List<Reservation>> getReservationsForCustomer(){
        return reservationDao.getReservationsByCustomerId(currentAccount.getUserID());
    }

}
