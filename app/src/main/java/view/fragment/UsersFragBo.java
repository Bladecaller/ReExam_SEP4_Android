package view.fragment;

import android.app.AlertDialog;
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
import android.widget.EditText;

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
    private Button createBtn, createNewUserBtn;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private String rights;
    private EditText idField, nameField, pwField, rightsField;

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
        createBtn = view.findViewById(R.id.createNewUser);
        mViewModel = new ViewModelProvider(this).get(BusinessOwnerViewModel.class);
        accountList = new ArrayList<>();

        customerList = new ArrayList<>();
        employeeList = new ArrayList<>();

        mViewModel.getCustomerAccounts().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                List<Account> toRemove = new ArrayList<>();
                customerList = customers;
                for(Customer cus: customers){
                    for(Account acc: accountList){
                        if(cus.getUserID() == acc.getUserID()){
                            toRemove.add(acc);
                        }
                    }
                }
                accountList.removeAll(toRemove);
                accountList.addAll(customerList);
                update();
            }
        });

        mViewModel.getEmployeeAccounts().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                List<Account> toRemove = new ArrayList<>();
                employeeList = employees;
                for(Employee emp: employees){
                    for(Account acc: accountList){
                        if(emp.getUserID() == acc.getUserID()){
                            toRemove.add(acc);
                        }
                    }
                }
                accountList.removeAll(toRemove);
                accountList.addAll(employeeList);
                update();
            }
        });

        initRecyclerView();

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewUserDialog();
            }
        });



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

    public void CreateNewUserDialog(){
        dialogBuilder = new AlertDialog.Builder(this.getContext());
        final View popup = getLayoutInflater().inflate(R.layout.popup,null);

        idField = popup.findViewById(R.id.idTxt);
        nameField = popup.findViewById(R.id.nameTxt);
        pwField = popup.findViewById(R.id.pwTxt);
        rightsField = popup.findViewById(R.id.newrightsTxt);
        createNewUserBtn = popup.findViewById(R.id.createUserBtn);
        rights = "";

        dialogBuilder.setView(popup);
        dialog = dialogBuilder.create();
        dialog.show();

        createNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rights = rightsField.getText().toString();
                System.out.println(rights);

                switch (rights){
                    case "User":
                        Customer customer = new Customer(Integer.parseInt(idField.getText().toString()),nameField.getText().toString(),pwField.getText().toString(),rights);
                        mViewModel.addCustomerAccount(customer);
                        dialog.dismiss();
                        break;

                    case "Supervisor":
                        Employee employee = new Employee(Integer.parseInt(idField.getText().toString()),nameField.getText().toString(),pwField.getText().toString(),rights);
                        mViewModel.addEmployeeAccount(employee);
                        dialog.dismiss();
                        break;
                }
            }
        });
    }

}