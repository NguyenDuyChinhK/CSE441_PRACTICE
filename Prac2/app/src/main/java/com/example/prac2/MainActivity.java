package com.example.prac2;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextStaffId, editTextFullName, editTextBirthDay, editTextSalary;
    TextView textViewEmployeeList, textViewStatus;
    Button buttonAddStaff;
    EmployeeViewModel employeeViewModel;
    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextStaffId = findViewById(R.id.editTextStaffId);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextBirthDay = findViewById(R.id.editTextBirthDay);
        editTextSalary = findViewById(R.id.editTextSalary);
        textViewEmployeeList = findViewById(R.id.textViewEmployeeList);
        textViewStatus = findViewById(R.id.textViewStatus);
        buttonAddStaff = findViewById(R.id.buttonAddStaff);


        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeList = new ArrayList<>();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };


        editTextStaffId.addTextChangedListener(textWatcher);
        editTextFullName.addTextChangedListener(textWatcher);
        editTextBirthDay.addTextChangedListener(textWatcher);
        editTextSalary.addTextChangedListener(textWatcher);

        employeeViewModel.getEmployeeList().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                employeeList = employees;
                updateEmployeeList();
            }
        });


        buttonAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewStaff();
            }
        });
    }

    private void updateStatus() {

        boolean hasInput = !editTextStaffId.getText().toString().trim().isEmpty() ||
                !editTextFullName.getText().toString().trim().isEmpty() ||
                !editTextBirthDay.getText().toString().trim().isEmpty() ||
                !editTextSalary.getText().toString().trim().isEmpty();


        if (hasInput) {
            textViewStatus.setText("Đã nhập nhưng chưa ấn nút");
        } else {
            textViewStatus.setText("Chưa nhập dữ liệu");
        }
    }

    private void addNewStaff() {
        String staffId = editTextStaffId.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String birthDay = editTextBirthDay.getText().toString().trim();
        float salary = Float.parseFloat("0" + editTextSalary.getText());

        if (TextUtils.isEmpty(staffId)) {
            textViewStatus.setText("Vui lòng nhập Staff ID.");
            return;
        }

        if (TextUtils.isEmpty(fullName)) {
            textViewStatus.setText("Vui lòng nhập Full Name.");
            return;
        }

        Employee newEmployee = new Employee(staffId, fullName, birthDay, salary);
        employeeViewModel.addEmployee(newEmployee);


        if (employeeList.size() == 1) {
            textViewStatus.setText("Đã nhấn nút thêm mới.");
        } else {
            textViewStatus.setText("Sau khi thêm vài nhân viên.");
        }


        editTextStaffId.setText("");
        editTextFullName.setText("");
        editTextBirthDay.setText("");
        editTextSalary.setText("");
    }

    private void updateEmployeeList() {
        StringBuilder sb = new StringBuilder();


        List<Employee> employees = employeeViewModel.getEmployeeList().getValue();

        if (employees == null || employees.isEmpty()) {
            sb.append("No result!");
        } else {
            for (Employee employee : employees) {

                if (!employee.getStaffId().isEmpty() && !employee.getFullName().isEmpty()) {
                    sb.append(employee.getStaffId()).append(" - ")
                            .append(employee.getFullName());


                    if (!TextUtils.isEmpty(employee.getBirthDay())) {
                        sb.append(" - ").append(employee.getBirthDay());
                    }


                    if (employee.getSalary() > 0) {
                        sb.append(" - ").append(employee.getSalary());
                    }


                    sb.append("\n");
                }
            }
        }


        if (sb.length() == 0) {
            sb.append("No result!");
        }

        textViewEmployeeList.setText(sb.toString());
    }


}

