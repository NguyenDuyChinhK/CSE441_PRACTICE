package com.example.prac2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel extends ViewModel {

    private final MutableLiveData<List<Employee>> employeeListLiveData = new MutableLiveData<>();
    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeViewModel() {
        employeeListLiveData.setValue(employeeList);
    }

    public LiveData<List<Employee>> getEmployeeList() {
        return employeeListLiveData;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employeeListLiveData.setValue(employeeList);
    }
}
