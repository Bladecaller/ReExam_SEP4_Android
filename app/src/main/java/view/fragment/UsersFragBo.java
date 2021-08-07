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

import com.example.sep4_android.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SaunaAdapter;
import adapter.UserAdapter;
import model.room.entity.Account.Account;
import model.room.entity.Account.BusinessOwner;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import view.activity.owner.UserViewBo;
import viewmodel.BusinessOwnerViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragBo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragBo extends Fragment implements UserAdapter.OnButtonListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragBo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragBo.
     */
    // TODO: Rename and change types and number of parameters
    private RecyclerView recyclerView;
    private List<Account> accountList;
    private List<Employee> employeeList;
    private List<Customer> customerList;
    private BusinessOwnerViewModel mViewModel;
    private UserAdapter adapter;

    public static UsersFragBo newInstance(String param1, String param2) {
        UsersFragBo fragment = new UsersFragBo();
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
        View view = inflater.inflate(R.layout.fragment_users_frag_bo, container, false);
        recyclerView = view.findViewById(R.id.rViewUsers);
        mViewModel = new ViewModelProvider(this).get(BusinessOwnerViewModel.class);
        accountList = new ArrayList<>();

        customerList = new ArrayList<>();
        employeeList = new ArrayList<>();


        mViewModel.getCustomerAccounts().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                customerList = customers;
                accountList.addAll(customerList);
                update();
            }
        });

        mViewModel.getEmployeeAccounts().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeList = employees;
                accountList.addAll(employeeList);
                update();

            }
        });

        initRecyclerView();

        return view;
    }

    public void initRecyclerView(){
        RecyclerView.LayoutManager linearLayoutMananger = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutMananger);
        adapter = new UserAdapter(getContext(),accountList,this);
        recyclerView.setAdapter(adapter);

    }

    public void update(){

        adapter = new UserAdapter(getContext(),accountList,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnButtonClick(int position) {
        Intent intent = new Intent(getContext(), UserViewBo.class);
        intent.putExtra("UserID",accountList.get(position).getUserID());
        intent.putExtra("UserAccount",accountList.get(position));
        startActivity(intent);
    }

}