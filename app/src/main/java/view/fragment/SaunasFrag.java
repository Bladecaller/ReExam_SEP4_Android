package view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SaunaAdapter;
import model.room.entity.Sauna.Sauna;
import view.activity.SaunaView;
import view.activity.customer.BookingViewCu;
import view.activity.customer.HomeViewCu;
import view.activity.employee.BookingViewEm;
import view.activity.employee.HomeViewEm;
import viewmodel.SaunaViewModel;

public class SaunasFrag extends Fragment implements SaunaAdapter.OnSaunaListener, SaunaAdapter.OnButtonListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public SaunasFrag() {

    }

    private Button bookingBtn;
    private SaunaViewModel saunaViewModel;
    private RecyclerView recyclerView;
    private SaunaAdapter adapter;
    private List<Sauna> saunaList;
    private ArrayList<Integer> imgList;
    private String rights;
    private int userID;


    public static SaunasFrag newInstance(String param1, String param2) {
        SaunasFrag fragment = new SaunasFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saunas, container, false);
        recyclerView = view.findViewById(R.id.rViewSaunas);
        saunaViewModel = new ViewModelProvider(this).get(SaunaViewModel.class);


        bookingBtn = view.findViewById(R.id.btnBooking);
        imgList = new ArrayList<>();

        imgList.add(R.drawable.sauna_1_c);
        imgList.add(R.drawable.sauna_2_c);
        imgList.add(R.drawable.sauna_3_c);
        imgList.add(R.drawable.sauna_4_c);
        imgList.add(R.drawable.sauna_5_c);
        imgList.add(R.drawable.sauna_6_c);
        imgList.add(R.drawable.sauna_7_c);
        imgList.add(R.drawable.sauna_1_c);
        imgList.add(R.drawable.sauna_2_c);

        try {
            HomeViewCu activity = (HomeViewCu) getActivity();
            rights = activity.getUserRights();
            userID = activity.getUserID();
        } catch (ClassCastException e){
            HomeViewEm activity = (HomeViewEm) getActivity();
            rights = activity.getUserRights();
        }

        saunaList = new ArrayList<>();

        saunaViewModel.getAllSaunas().observe(getViewLifecycleOwner(), new Observer<List<Sauna>>() {
            @Override
            public void onChanged(List<Sauna> saunas) {
                saunaList = saunas;
                update();
            }
        });
        initRecyclerView();
        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new SaunaAdapter(getContext(),
                saunaList,
                imgList,
                this,
                this);
        recyclerView.setAdapter(adapter);
    }

    public void update(){

        adapter = new SaunaAdapter(getContext(), saunaList,imgList,this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSaunaClick(int position) {
        Intent intent = new Intent(this.getContext(), SaunaView.class);
        intent.putExtra("Sauna", saunaList.get(position).getSaunaID());
        intent.putExtra("UserID", rights);
        startActivity(intent);
    }

    @Override
    public void onButtonClick(int position) {
       switch (rights){

            case "Supervisor":
                Intent intent = new Intent(getContext(), BookingViewEm.class);
                intent.putExtra("SaunaID", saunaList.get(position).getSaunaID());
                intent.putExtra("UserID","Supervisor");
                startActivity(intent);
                break;

            case "User":
                intent = new Intent(getContext(), BookingViewCu.class);
                intent.putExtra("SaunaID", saunaList.get(position).getSaunaID());
                intent.putExtra("UserID",userID);
                startActivity(intent);
                break;
        }
    }
}