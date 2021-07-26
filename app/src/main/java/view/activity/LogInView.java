package view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import api.InterfaceAPI;
import api.MyRetrofit;

import com.example.sep4_android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.customer.HomeViewCu;
import view.activity.employee.HomeViewEm;
import view.activity.owner.HomeViewBo;

public class LogInView extends AppCompatActivity {

    private Button logInButton;
    private ProgressBar pbar;
    private MyRetrofit retrofit;
    private InterfaceAPI api;
    private EditText usernameField, pwField;
    private String username, pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_log_in_view);
        usernameField = findViewById(R.id.usernameText);
        //Log.d("EDIT TEXT ", username.toString());
        //retrofit = new MyRetrofit();
        retrofit = new MyRetrofit();
        api = retrofit.api;


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
                        username = usernameField.getText().toString();
                        postCall(username);
                        switch (username){

                            case "":
                                Toast.makeText(v.getContext(),"Fill In All The Fields",Toast.LENGTH_SHORT).show();
                                break;

                            case "Owner":
                                openHomePageBo();
                                break;

                            case "Employee":
                                openHomePageEm();
                                break;

                            case "Customer":
                                openHomePageCu();
                                break;
                        }
                        pbar.setVisibility(View.INVISIBLE);
                    }
                }.start();
            }

        });


    }
    public void openHomePageBo(){
        Intent intent = new Intent(this, HomeViewBo.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(intent);
        }

    }
    public void openHomePageCu(){
        Intent intent = new Intent(this, HomeViewCu.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(intent);
        }

    }

    public void openHomePageEm(){
        Intent intent = new Intent(this, HomeViewEm.class);
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