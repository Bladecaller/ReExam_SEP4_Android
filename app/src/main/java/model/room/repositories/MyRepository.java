package model.room.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Arrays;
import java.util.List;

import api.MyRetrofit;
import model.room.dao.AccountsDao;
import model.room.dao.DataPointDao;
import model.room.dao.ReservationDao;
import model.room.dao.SaunasDao;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import model.room.entity.Account.Reservation;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Account.RightsEnumConverter;
import model.room.entity.Sauna.DataPoint;
import model.room.entity.Sauna.Sauna;
import model.room.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {
    private MyRetrofit retrofit;
    public Account currentAccount;
    private AccountsDao accountsDao;
    private DataPointDao dataPointDao;
    private ReservationDao reservationDao;
    private SaunasDao saunasDao;

    private LiveData<List<Account>> accounts;
    private LiveData<List<Sauna>> saunas;
    private LiveData<List<Reservation>> reservations;
    private LiveData<List<DataPoint>> datapoints;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public MyRepository(Application application) {
        retrofit = new MyRetrofit();

        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);

        accountsDao = db.accountsDao();
        dataPointDao = db.dataPointDao();
        reservationDao = db.reservationDao();
        saunasDao = db.saunaDao();
        LiveData<List<Account>> temp = null;
        for(BusinessOwner acc:accountsDao.getAllBusinessOwners().getValue()){
            temp.getValue().add(acc);
        }
        for(Customer acc:accountsDao.getAllCustomers().getValue()){
            temp.getValue().add(acc);
        }
        for(Employee acc:accountsDao.getAllEmployees().getValue()){
            temp.getValue().add(acc);
        }
        accounts = temp;
        datapoints = dataPointDao.getAllDataPoints();
        reservations = reservationDao.getAllReservations();
        saunas = saunasDao.getAllSaunas();


    }
    //-----------API methods---------------------------------------------------------------------------

    //API Get all accounts, empty local database, and insert the new list, invoke after any accounts data manipulation !
    public void populateAccountsRepo(){
        Call<List<Account>> call = retrofit.api.getAllAccounts();
        call.enqueue(new Callback<List<Account>>(){
            @Override
            public void onResponse (Call <List<Account>> call, Response<List<Account>> response){
                System.out.println("SUCCESS " + response.body());
                emptyAccountRepo();
                List<Account> temp;
                Account[] accArray = retrofit.gson.fromJson(response.body().toString(), Account[].class);
                temp = Arrays.asList(accArray);
                for(Account acc : temp){
                    accountInsert(acc);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println("Failed at populateAccountsRepo");
            }

        });
    }

    public void addACustomerAccount(Customer account){
        Call call = retrofit.api.createNewCustomerAccount(account.getUsername(),account.getPassword(), account.getRights(), account.getRoomNumber());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }
    public void addAEmployeeAccount(Employee account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }
    public void addABusinessOwnerAccount(BusinessOwner account){
        Call call = retrofit.api.createNewAccount(account.getUsername(),account.getPassword(), account.getRights());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }

    public void removeASingleAccount(int accountID){
        Call call = retrofit.api.removeUser(accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at addASingleAccount");
            }
        });
    }
    public void setRights(int accountID, RightsEnum rightsEnum){
        Call call = retrofit.api.setRights(RightsEnumConverter.fromRightsEnumToInt(rightsEnum), accountID);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setRights");
            }
        });

    }
    public void populateSaunasRepo(){
        Call<List<Sauna>> call = retrofit.api.getAllSaunas();
        call.enqueue(new Callback<List<Sauna>>(){
            @Override
            public void onResponse (Call <List<Sauna>> call, Response<List<Sauna>> response){
                System.out.println("SUCCESS " + response.body());
                emptySaunaRepo();
                List<Sauna> temp;
                Sauna[] array = retrofit.gson.fromJson(response.body().toString(), Sauna[].class);
                temp = Arrays.asList(array);
                for(Sauna obj : temp){
                    saunaInsert(obj);
                }
            }

            @Override
            public void onFailure(Call<List<Sauna>> call, Throwable t) {
                System.out.println("Failed at populateSaunasRepo");
            }

        });
    }
    public void populateReservationRepo(){
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
    public void populateDatapointRepo(Sauna sauna){
        Call<List<DataPoint>> call = retrofit.api.getAllDataPoints(sauna.getId());
        call.enqueue(new Callback<List<DataPoint>>(){
            @Override
            public void onResponse (Call <List<DataPoint>> call, Response<List<DataPoint>> response){
                System.out.println("SUCCESS " + response.body());
                emptyDataRepo();
                List<DataPoint> temp;
                DataPoint[] array = retrofit.gson.fromJson(response.body().toString(), DataPoint[].class);
                temp = Arrays.asList(array);
                for(DataPoint obj : temp){
                    datapointInsert(obj);
                }
            }

            @Override
            public void onFailure(Call<List<DataPoint>> call, Throwable t) {
                System.out.println("Failed at populateDatapointRepo");
            }

        });
    }

    public void login(String username, String password){
        final Account[] acc = {null};
        Call<Account> call = retrofit.api.logIn(username,password);
        call.enqueue(new Callback<Account>(){
            @Override
            public void onResponse (Call <Account> call, Response<Account> response){
                System.out.println("SUCCESS " + response.body());
                acc[0] = retrofit.gson.fromJson(response.body().toString(), Account.class);
                currentAccount = acc[0];
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("Failed at Login");
            }

        });
    }

    public void setTresholds(float CO2, float humidity, float temperature){
        Call call = retrofit.api.setThresholds(temperature,humidity,CO2);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateSaunasRepo();
                populateAccountsRepo();
                populateReservationRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at setThresholds");
            }
        });
    }

    public void openDoor(Sauna sauna){
        Call call = retrofit.api.openTheDoor(sauna.getId());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateSaunasRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at OpenDoor");
            }
        });
    }

    public void createReservation(Reservation reservation){
        Call call = retrofit.api.createReservation(reservation.getCustomerId(),
                reservation.getSaunaId(),reservation.getRoomNumber(),
                reservation.getBookTimeFrom(), reservation.getBookTimeTo());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                populateAccountsRepo();
                populateReservationRepo();
                populateSaunasRepo();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("Failed at CreateReservation");
            }
        });
    }

    public Sauna checkNotifications(){
        Sauna[] sauna = {null};
        Call<Sauna> call = retrofit.api.checkNotification();
        call.enqueue(new Callback<Sauna>() {
            @Override
            public void onResponse(Call<Sauna> call, Response<Sauna> response) {
                sauna[0] = retrofit.gson.fromJson(response.body().toString(), Sauna.class);
            }

            @Override
            public void onFailure(Call<Sauna> call, Throwable t) {
                System.out.println("Failed at Notifications");
            }
        });
        return sauna[0];
    }

    public LiveData<List<Reservation>> getPersonalReservations(){
        return getReservationsForCustomer((Customer)currentAccount);
    }

    //---------Room methods--------------------------------------------------------------------------------

    //-----------Account-------------------------------------------------------------------------------

    //store a single account
    public void accountInsert(Account account) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.insert(account);
        });
    }

    //delete all accounts
    public void emptyAccountRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            accountsDao.deleteAll();
        });
    }

    // return a list of all accounts to the viewmodel
    public LiveData<List<Customer>> getCustomers(){
        return accountsDao.getAllCustomers();
    }
    public LiveData<List<Employee>> getEmployees(){
        return accountsDao.getAllEmployees();
    }
    public LiveData<List<BusinessOwner>> getBusinessOwners(){
        return accountsDao.getAllBusinessOwners();
    }
    public LiveData<List<Account>> getAllAccounts(){ return accounts;}

    //------------Datapoint---------------------------------------------------------------------------------

    //store a single datapoint
    public void datapointInsert(DataPoint dataPoint){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataPointDao.insert(dataPoint);
        });
    }

    //delete all datapoints
    public void emptyDataRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            dataPointDao.deleteAll();
        });
    }

    // return all datapoints for a specific sauna
    public LiveData<List<DataPoint>> getAllDataPoints(){
        return dataPointDao.getAllDataPoints();
    }

    //--------Reservation--------------------------------------------------------------------------------------

    //store a single reservation
    public void reservationInsert(Reservation reservation){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.insertReservation(reservation);
        });
    }

    //delete all reservations
    public void emptyReservationRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.deleteAll();
        });
    }

    //return all reservations
    public LiveData<List<Reservation>> getAllReservations(){
        return reservationDao.getAllReservations();
    }

    // return all reservations for a specific customer
    public LiveData<List<Reservation>> getReservationsForCustomer(Customer customer){
        return reservationDao.getReservationsByCustomerId(customer.getUserID());
    }

    //---------Sauna--------------------------------------------------------------------------------------------

    //store a single sauna
    public void saunaInsert(Sauna sauna){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.insert(sauna);
        });
    }

    //delete all saunas
    public void emptySaunaRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            saunasDao.deleteAll();
        });
    }

    //return all saunas
    public LiveData<List<Sauna>> getAllSaunas(){
        return saunasDao.getAllSaunas();
    }

}
