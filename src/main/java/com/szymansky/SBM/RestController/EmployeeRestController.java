package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeRestController {

    private List<Employee> employeeList;


    @GetMapping("/{id}")
    public Employee getEmployees(@PathVariable long id){
    return employeeList.stream()
    .filter(employee -> employee.getId()==id).findFirst().get();
     }

     @GetMapping
    public List<Employee> getEmployees() {return employeeList;}

    @PostMapping
    public boolean addEmployee (@RequestBody Employee employee){return employeeList.add(employee);}
}

