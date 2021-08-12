package view.activity.employee;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.room.entity.IntegerEntity;
import viewmodel.EmployeeViewModel;

public class SettingsViewEm extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;

    private SwitchCompat notificationsSwitch;
    private List<IntegerEntity> saunaIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        setContentView(R.layout.activity_settings_view_em);

        notificationsSwitch = findViewById(R.id.switchEm);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Sauna");
        builder.setContentText("Threshold Exceeded");
        builder.setSmallIcon(R.drawable.icon_sauna_filled_white);

        Intent notificationIntent = new Intent(this,SettingsViewEm.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,2,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        employeeViewModel.getAllNotifiedSaunas().observe(this, new Observer<List<IntegerEntity>>() {
            @Override
            public void onChanged(List<IntegerEntity> accounts) {
                saunaIDs = accounts;
            }
        });

        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked = true){
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {

                            employeeViewModel.checkforNotifications();
                            if(!saunaIDs.isEmpty()){
                                List<IntegerEntity> toRemove = new ArrayList<>();
                                for (IntegerEntity inte: saunaIDs){
                                    System.out.println("Sauna " +inte.getSaunaID() + "needs attention");
                                }
                                for(IntegerEntity ent: saunaIDs){
                                    toRemove.add(ent);
                                }
                                saunaIDs.removeAll(toRemove);

                            }

                        }

                    }, 0, 10000);//put here time 1000 milliseconds=1 second

                }manager.notify(2, builder.build());
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }
    }
}