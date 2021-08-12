package view.activity.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;

import model.room.entity.Sauna.Sauna;
import viewmodel.BusinessOwnerViewModel;

public class SettingsViewBo extends AppCompatActivity {

    private Button tBtn;
    private EditText co2Field,tempField,humField;
    private BusinessOwnerViewModel businessOwnerViewModel;
    private List<Sauna> saunaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_settings_view);
        tBtn = findViewById(R.id.thresholdBtn);
        co2Field = findViewById(R.id.thresholdValCO2);
        tempField = findViewById(R.id.thresholdValTemp);
        humField = findViewById(R.id.thresholdValHum);
        saunaList = new ArrayList<>();

        businessOwnerViewModel = new ViewModelProvider(this).get(BusinessOwnerViewModel.class);
        businessOwnerViewModel.getSaunas().observe(this, new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                saunaList = saunas;
            }
        });

        tBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i< saunaList.size(); i++){
                    businessOwnerViewModel.setThresholds(Float.parseFloat(co2Field.getText().toString())
                            ,Float.parseFloat(humField.getText().toString())
                            ,Float.parseFloat(tempField.getText().toString()),saunaList.get(i));
                }
                Toast.makeText(v.getContext(),"Threshold Set",Toast.LENGTH_SHORT).show();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }
    }
}