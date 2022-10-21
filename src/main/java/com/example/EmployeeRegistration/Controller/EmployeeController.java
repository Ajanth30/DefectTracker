package com.example.EmployeeRegistration.Controller;

import com.example.EmployeeRegistration.Dto.EmployeeDto;
import com.example.EmployeeRegistration.Dto.UserDto;
import com.example.EmployeeRegistration.Entity.Employee;
import com.example.EmployeeRegistration.Services.EmailService;
import com.example.EmployeeRegistration.Services.EmpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static com.example.EmployeeRegistration.Constants.SAVE_DEFECT_USER;

@RestController
public class EmployeeController {
    @Autowired
    private EmpService empService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private WebClient webClient;
    //"http://localhost:8084/saveUser"

    List<String> designations= Arrays.asList("QA","SoftwareEngineer","HR");


    @PostMapping("/employee")
    public ResponseEntity<Object> saveEmp(@RequestBody EmployeeDto employeeDto){
        if(empService.isAlreadyExists(employeeDto.getEmail())){
            return ResponseEntity.ok("User Already exists");
        }
        else {
            Employee employee=modelMapper.map(employeeDto,Employee.class);
            Employee employee1=empService.SaveEmp(employee);
            UserDto userDto=new UserDto();
            for(String i:designations){
                if(employeeDto.getDesignation().contains(i)){
                    userDto.setUserName(employee1.getEmail());
                    userDto.setPassword(employee1.getPassword());
                    userDto.setDesignation(i);
                    userDto.setEmp_id(employee1.getId());
                    /* String body="Hi"+", "+employeeDto.getName()+"\n"+"You can LogIn Use the Provided Password\n"+"Password : -"+employeeDto.getPassword()+
                    "\nTo confirm your account, please click here : " +"http://localhost:8080/confirm-account?;";
                    emailService.SendVerificationMail(employeeDto.getEmail(),body);*/
                    webClient.post().uri(SAVE_DEFECT_USER).bodyValue(userDto)
                            .retrieve().bodyToMono(String.class).block();
                    break;
                }

            }

        }
        return ResponseEntity.ok("Created");
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmp(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(empService.getEmpById(id));
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmp(@PathVariable("id") int id){
        empService.deleteEmpById(id);
        return ResponseEntity.ok("Deleted");
    }


    }


