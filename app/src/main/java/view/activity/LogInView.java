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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sep4_android.R;

public class LogInView extends AppCompatActivity {

    private Button logInButton;
    private ProgressBar pbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
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
}