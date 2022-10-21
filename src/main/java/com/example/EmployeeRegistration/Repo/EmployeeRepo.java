package com.example.EmployeeRegistration.Repo;

import com.example.EmployeeRegistration.Dto.EmployeeDto;
import com.example.EmployeeRegistration.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    boolean existsByEmail(String email);


}
