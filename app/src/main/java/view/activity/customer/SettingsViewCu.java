package view.activity.customer;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.sep4_android.R;

import view.activity.customer.HomeViewCu;

public class SettingsViewCu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_settings_view_cu);


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }
    }

}