package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final EmployeeRepository emlpoyeRepo;

    @PostMapping("/dodaj")
    private void InitializeEmployees(){
        System.out.println("Adding employees");
        Employee employee1 = new Employee(1L,"Jan","Borys");
        Employee employee2 = new Employee(2L,"Kazimierz","Jan");
        Employee employee3 = new Employee(3L, "Borys","Kazimierz");
        emlpoyeRepo.save(employee1);
        emlpoyeRepo.save(employee2);
        emlpoyeRepo.save(employee3);
        System.out.println("dodano");
    }
}

