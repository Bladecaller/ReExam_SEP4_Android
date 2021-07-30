package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyRetrofit{

    public InterfaceAPI api;
    public Gson gson;
    final RuntimeTypeAdapterFactory<Account> typeFactory = RuntimeTypeAdapterFactory
            .of(Account.class, "rights") // Here you specify which is the parent class and what field particularizes the child class.
            .registerSubtype(Customer.class, "0") // if the flag equals the class name, you can skip the second parameter. This is only necessary, when the "type" field does not equal the class name.
            .registerSubtype(Employee.class, "1")
            .registerSubtype(BusinessOwner.class, "2");

    public MyRetrofit(){
        gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(typeFactory)
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://8a1485d5-2ffa-4ff4-8b88-3549c94f8b64.mock.pstmn.io/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.api = retrofit.create(InterfaceAPI.class);

    }
}
