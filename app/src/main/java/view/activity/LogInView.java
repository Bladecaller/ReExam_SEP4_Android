package view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

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

import java.util.List;

import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Sauna.Sauna;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.customer.HomeViewCu;
import view.activity.employee.HomeViewEm;
import view.activity.owner.HomeViewBo;
import viewmodel.EmployeeViewModel;
import viewmodel.LoginViewModel;

public class LogInView extends AppCompatActivity {

    private Button logInButton;
    private ProgressBar pbar;
    private MyRetrofit retrofit;
    private InterfaceAPI api;
    private EditText usernameField, pwField;
    private String username, pw;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }

        setContentView(R.layout.activity_log_in_view);
        usernameField = findViewById(R.id.usernameText);
        pwField = findViewById(R.id.pwText);

        retrofit = new MyRetrofit();
        api = retrofit.api;

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        logInButton = findViewById(R.id.logInButton);
        pbar = findViewById(R.id.progressBar);



        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        pbar.setVisibility(View.VISIBLE);

                        username = usernameField.getText().toString();
                        pw = pwField.getText().toString();

                        if (username.equals("") || pw.equals("")){
                            Toast.makeText(v.getContext(),"Fill In All The Fields",Toast.LENGTH_SHORT).show();
                        }

                        loginViewModel.login(username,pw);

                        loginViewModel.getCurrentAccountType();
                        String type = loginViewModel.getCurrentAccountType();

                        switch (type){
                            case "":
                                Toast.makeText(v.getContext(),"Error",Toast.LENGTH_SHORT).show();
                                break;

                            case "Owner":
                                openHomePageBo();
                                break;

                            case "Supervisor":
                                openHomePageEm();
                                break;

                            case "User":
                                openHomePageCu();
                                break;
                        }
                        pbar.setVisibility(View.INVISIBLE);


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
}