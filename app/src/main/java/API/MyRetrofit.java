package API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyRetrofit{

    public InterfaceAPI api;

    public MyRetrofit(){
        Gson gson;
        gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ptsv2.com/t/l3son-1626629720/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.api = retrofit.create(InterfaceAPI.class);

    }
}
