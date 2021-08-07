package view.activity.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.sep4_android.R;

import java.util.ArrayList;

import model.room.entity.Account.Account;
import model.room.entity.Account.RightsEnum;
import model.room.entity.Sauna.Sauna;
import viewmodel.BusinessOwnerViewModel;
import viewmodel.SaunaViewModel;

public class UserViewBo extends AppCompatActivity {

    private Button deleteBtn,changeBtn;
    private Spinner userSpinner;
    private ArrayList<String> rightsList;
    private BusinessOwnerViewModel mViewModel;
    private Account account;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_bo);

        deleteBtn = findViewById(R.id.deleteBtn);
        changeBtn = findViewById(R.id.changeRightsBtn);
        userSpinner = findViewById(R.id.spinnerRights);
        rightsList = new ArrayList<>();
        rightsList.add("User");
        rightsList.add("Supervisor");
        rightsList.add("Owner");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,rightsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        userID = String.valueOf(extras.getInt("UserID"));
        account = (Account) extras.getSerializable("UserAccount");

        mViewModel = new ViewModelProvider(this).get(BusinessOwnerViewModel.class);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setRights(account,userSpinner.getSelectedItem().toString());

            }
        });



    }
}