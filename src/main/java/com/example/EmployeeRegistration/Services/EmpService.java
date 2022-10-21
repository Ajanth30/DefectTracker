package com.example.EmployeeRegistration.Services;

import com.example.EmployeeRegistration.Dto.EmployeeDto;
import com.example.EmployeeRegistration.Entity.Employee;

public interface EmpService {
    Employee SaveEmp(Employee employee);
    boolean isAlreadyExists(String email);
    Employee getEmpById(int id);
    void deleteEmpById(int id);
}
