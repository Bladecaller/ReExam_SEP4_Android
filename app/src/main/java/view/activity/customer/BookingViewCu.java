package view.activity.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sep4_android.R;

import model.room.entity.Account.Reservation;
import viewmodel.CustomerViewModel;
import viewmodel.LoginViewModel;

public class BookingViewCu extends AppCompatActivity {
    private TextView titleTxt;
    private String rights;
    private int userID, saunaID;
    private EditText fromTxt, toTxt;
    private Button bookBtn;
    private CustomerViewModel customerViewModel;
    private Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking_view_cu);
        titleTxt = findViewById(R.id.bookingTitleTxt);
        fromTxt = findViewById(R.id.fromTime);
        toTxt = findViewById(R.id.toTime);
        bookBtn = findViewById(R.id.bookNowbtn);

        Bundle extras = getIntent().getExtras();
        saunaID = extras.getInt("SaunaID");
        rights = String.valueOf(extras.getInt("SaunaID"));
        userID = extras.getInt("UserID");
        titleTxt.setText("Sauna " + rights);

        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservation = new Reservation(userID,saunaID,String.valueOf(fromTxt.getText()),String.valueOf(toTxt.getText()));
                customerViewModel.book(reservation);
                Toast.makeText(v.getContext(),"Reservation Made",Toast.LENGTH_SHORT).show();
            }
        });

    }
}