package view.activity.customer;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.sep4_android.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import adapter.PageTransformer;
import adapter.SectionsPageAdapterCu;
import view.activity.LogInView;

public class HomeViewCu extends AppCompatActivity {


private ImageButton settingsBtn;
private TextView titleW;
private String rights;
private int userID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_home_view_bo);
        settingsBtn = findViewById(R.id.settingsBtn);
        titleW = findViewById(R.id.bookingTitleTxt);
        SectionsPageAdapterCu sectionsPagerAdapter = new SectionsPageAdapterCu(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        PageTransformer pageT = new PageTransformer();
        viewPager.setPageTransformer(true,pageT);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabs.getTabAt(0)).select();

        Bundle extras = getIntent().getExtras();
        rights = "User";
        userID = extras.getInt("UserID");


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.bgDark));
        }


        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });



    }

    public void openSettings(){
        Intent intent = new Intent(this, SettingsViewCu.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivity(intent);
        }
    }


    public String getUserRights(){
        return rights;
    }

    public int getUserID(){
        return userID;
    }

}