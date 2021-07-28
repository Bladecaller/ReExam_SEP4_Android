package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyRetrofit{

    public InterfaceAPI api;
    public Gson gson;

    public MyRetrofit(){
        gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://8a1485d5-2ffa-4ff4-8b88-3549c94f8b64.mock.pstmn.io/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.api = retrofit.create(InterfaceAPI.class);

    }
}
