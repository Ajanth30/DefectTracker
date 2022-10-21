package com.example.EmployeeRegistration.Services;

import com.example.EmployeeRegistration.Dto.EmployeeDto;
import com.example.EmployeeRegistration.Entity.Employee;
import com.example.EmployeeRegistration.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServices implements EmpService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public Employee SaveEmp(Employee employee) {
        employeeRepo.save(employee);
        return employee;
    }

    @Override
    public boolean isAlreadyExists(String email) {
        return employeeRepo.existsByEmail(email);
    }

    @Override
    public Employee getEmpById(int id) {
        return employeeRepo.findById(id).orElse(new Employee());
    }

    @Override
    public void deleteEmpById(int id) {
        employeeRepo.deleteById(id);
    }

}
