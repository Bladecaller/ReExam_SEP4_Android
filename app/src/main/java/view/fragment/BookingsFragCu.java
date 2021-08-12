package view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;

import adapter.BookingAdapter;
import model.room.entity.Account.Reservation;
import view.activity.customer.HomeViewCu;
import viewmodel.CustomerViewModel;

public class BookingsFragCu extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BookingsFragCu() {

    }

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private List<Reservation> reservationList;
    private CustomerViewModel customerViewModel;
    private int userID;

    public static BookingsFragCu newInstance(String param1, String param2) {
        BookingsFragCu fragment = new BookingsFragCu();
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

        View view = inflater.inflate(R.layout.fragment_subscription_frag_cu, container, false);
        recyclerView = view.findViewById(R.id.recyclerReservations);
        HomeViewCu activity = (HomeViewCu) getActivity();

        userID = activity.getUserID();
        reservationList = new ArrayList<>();

        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        customerViewModel.getPersonalReservations(userID).observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                reservationList = reservations;
                System.out.println(reservationList.size()+"_____________________________________________________");
                update();
            }
        });
        initRecyclerView();
        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new BookingAdapter(getContext(),reservationList);
        recyclerView.setAdapter(adapter);

    }

    public void update(){
        adapter = new BookingAdapter(getContext(),reservationList);
        recyclerView.setAdapter(adapter);
    }
}