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

import adapter.UserAdapter;
import model.room.entity.Account.Account;
import model.room.entity.Account.Customer;
import model.room.entity.Account.Employee;
import view.activity.owner.UserViewBo;
import viewmodel.BusinessOwnerViewModel;

public class UsersFragBo extends Fragment implements UserAdapter.OnButtonListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UsersFragBo() {

    }

    private RecyclerView recyclerView;
    private List<Account> accountList;
    private List<Employee> employeeList;
    private List<Customer> customerList;
    private BusinessOwnerViewModel businessOwnerViewModel;
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

        View view = inflater.inflate(R.layout.fragment_users_frag_bo, container, false);
        recyclerView = view.findViewById(R.id.rViewUsers);
        createBtn = view.findViewById(R.id.createNewUser);
        businessOwnerViewModel = new ViewModelProvider(this).get(BusinessOwnerViewModel.class);
        accountList = new ArrayList<>();

        customerList = new ArrayList<>();
        employeeList = new ArrayList<>();

        businessOwnerViewModel.getCustomerAccounts().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
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

        businessOwnerViewModel.getEmployeeAccounts().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
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
                switch (rights){
                    case "User":
                        Customer customer = new Customer(Integer.parseInt(idField.getText().toString()),nameField.getText().toString(),pwField.getText().toString(),rights);
                        businessOwnerViewModel.addCustomerAccount(customer);
                        dialog.dismiss();
                        break;

                    case "Supervisor":
                        Employee employee = new Employee(Integer.parseInt(idField.getText().toString()),nameField.getText().toString(),pwField.getText().toString(),rights);
                        businessOwnerViewModel.addEmployeeAccount(employee);
                        dialog.dismiss();
                        break;
                }
            }
        });
    }

}