package view.activity.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sep4_android.R;

public class SaunaActivityCu extends AppCompatActivity {

    private TextView saunaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sauna_cu);
        saunaName = findViewById(R.id.saunaName);
        /*String name = (String) savedInstanceState.getSerializable("Sauna");
        saunaName.setText(name);*/
    }
}