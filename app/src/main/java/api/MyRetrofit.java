package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyRetrofit{

    public InterfaceAPI api;
    public Gson gson;
    final RuntimeTypeAdapterFactory<Account> typeFactory = RuntimeTypeAdapterFactory
            .of(Account.class, "Rights")
            .registerSubtype(Employee.class, "Supervisor")
            .registerSubtype(Customer.class, "User      ")
            .registerSubtype(BusinessOwner.class, "Owner     ");

    public MyRetrofit(){
        gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(typeFactory)
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sep4re.azurewebsites.net/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.api = retrofit.create(InterfaceAPI.class);

    }
}
