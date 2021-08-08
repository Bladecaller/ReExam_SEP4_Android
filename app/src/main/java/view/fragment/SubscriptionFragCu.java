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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubscriptionFragCu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscriptionFragCu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SubscriptionFragCu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscriptionFragCu.
     */
    // TODO: Rename and change types and number of parameters

    private RecyclerView recyclerView;
    private BookingAdapter adapter;
    private List<Reservation> reservationList;
    private CustomerViewModel mViewModel;
    private int userID;

    public static SubscriptionFragCu newInstance(String param1, String param2) {
        SubscriptionFragCu fragment = new SubscriptionFragCu();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription_frag_cu, container, false);
        recyclerView = view.findViewById(R.id.recyclerReservations);
        HomeViewCu activity = (HomeViewCu) getActivity();

        userID = activity.getUserID();
        reservationList = new ArrayList<>();

        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        mViewModel.getPersonalReservations(userID).observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
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