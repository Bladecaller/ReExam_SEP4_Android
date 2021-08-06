package view.activity.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sep4_android.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import model.room.entity.Account.Customer;
import model.room.entity.Account.Reservation;
import viewmodel.CustomerViewModel;
import viewmodel.EmployeeViewModel;

public class BookingViewEm extends AppCompatActivity {

    private TextView titleTxt;
    private String name;
    private int userID, saunaID;
    private EditText fromTxt, toTxt;
    private Button bookBtn;
    private EmployeeViewModel employeeViewModel;
    private Reservation reservation;
    private List<Customer> customerList;
    private ArrayList<String> customerIDList = new ArrayList<String>() ;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view_em);


        titleTxt = findViewById(R.id.bookingTitleTxt);
        fromTxt = findViewById(R.id.fromTime);
        toTxt = findViewById(R.id.toTime);
        bookBtn = findViewById(R.id.bookNowbtn);
        spinner = findViewById(R.id.spinnerUser);


        Bundle extras = getIntent().getExtras();
        saunaID = extras.getInt("SaunaID");
        name = String.valueOf(extras.getInt("SaunaID"));
        titleTxt.setText("Sauna " + name);



        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeViewModel.getCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                customerList = customers;

                for (int i = 0; i < customerList.size(); i++){
                    customerIDList.add(String.valueOf(customerList.get(i).getUserID()));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,customerIDList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });




        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = Integer.parseInt(spinner.getSelectedItem().toString());
                reservation = new Reservation(userID,saunaID,String.valueOf(fromTxt.getText()),String.valueOf(toTxt.getText()));
                employeeViewModel.book(reservation);
                Toast.makeText(v.getContext(),"Reservation Made",Toast.LENGTH_SHORT).show();
            }
        });
    }
}