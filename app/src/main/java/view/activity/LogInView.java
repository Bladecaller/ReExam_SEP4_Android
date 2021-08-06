package view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import model.room.entity.Account.CurrentAccount;
import view.activity.customer.HomeViewCu;
import view.activity.employee.HomeViewEm;
import view.activity.owner.HomeViewBo;
import viewmodel.LoginViewModel;

public class LogInView extends AppCompatActivity {

    private Button logInButton;
    private ProgressBar pbar;
    private MyRetrofit retrofit;
    private InterfaceAPI api;
    private EditText usernameField, pwField;
    private String username, pw;
    private LoginViewModel loginViewModel;
    private List<CurrentAccount> currentAccounts;


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



        loginViewModel.getCurrentAcc().observe(this, new Observer<List<CurrentAccount>>() {
            @Override
            public void onChanged(List<CurrentAccount> accounts) {

                currentAccounts = accounts;
                String type = null;

                if(currentAccounts.size() != 0 ) type = currentAccounts.get(0).getRights();
                System.out.println("SIZE IS "+ currentAccounts.size());
                System.out.println("TYPE IS "+ type);
                if(type==null){
                    System.out.println("TYPE IS "+ " zero");
                }else
                    switch (type){
                        case "":
                            //Toast.makeText(loginViewModel.getApplication(),"Error",Toast.LENGTH_SHORT).show();
                            break;

                        case "Owner     ":
                            openHomePageBo();
                            break;

                        case "Supervisor":
                            openHomePageEm();
                            break;

                        case "User      ":
                            openHomePageCu();
                            break;
                    }
                pbar.setVisibility(View.INVISIBLE);
            }
        });




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