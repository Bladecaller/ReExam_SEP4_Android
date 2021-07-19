package view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import API.InterfaceAPI;
import API.MyRetrofit;

import com.example.sep4_android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class LogInView extends AppCompatActivity {

    private Button logInButton;
    private ProgressBar pbar;
    private MyRetrofit retrofit;
    private InterfaceAPI api;
    private EditText username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        username = (EditText)findViewById(R.id.usernameText);
        //Log.d("EDIT TEXT ", username.toString());
        //retrofit = new MyRetrofit();
        retrofit = new MyRetrofit();
        api = retrofit.api;
        setContentView(R.layout.activity_log_in_view);

        logInButton = findViewById(R.id.logInButton);
        pbar = findViewById(R.id.progressBar);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new CountDownTimer(2000,2000){

                   @Override
                   public void onTick(long millisUntilFinished) {
                       pbar.setVisibility(View.VISIBLE);
                   }

                   @Override
                   public void onFinish() {
                       postCall("Jack");//username.getText().toString());
                       openHomePage();
                       pbar.setVisibility(View.INVISIBLE);
                   }
               }.start();
            }

        });


    }
    public void openHomePage(){
        Intent intent = new Intent(this, HomeView.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(intent);
        }

    }
    public void postCall(String id){
        Call<String> call = api.post(id);
        call.enqueue(new Callback<String>(){
            @Override
            public void onResponse (Call <String> call, Response<String> response){
                System.out.println("SUCCESS " + response.body());
            }

            @Override
            public void onFailure (Call <String> call, Throwable t){
                System.out.println("Failed controlled " + t);
            }
        });
    }
}