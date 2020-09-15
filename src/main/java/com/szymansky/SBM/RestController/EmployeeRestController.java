package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeRestController {

    private List<Employee> employeeList;

    private final EmployeeRepository emlpoyeRepo;


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Employee employee = emlpoyeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        return ResponseEntity.ok().body(employee);
    }

    //wyszukiwanie wszystkich obiektów
    @GetMapping("")
    public List<Employee> getEmployees() {
        return emlpoyeRepo.findAll();
    }

    //add new record
    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee) {
        return emlpoyeRepo.save(employee);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employeeDettails)
            throws ResourceNotFoundException {
        Employee employee = emlpoyeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        employee.setId(employeeDettails.getId());
        employee.setName(employeeDettails.getName());
        employee.setLastName(employeeDettails.getLastName());
        final Employee updatedEmployee = emlpoyeRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Employee employee = emlpoyeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id::" + id));

        emlpoyeRepo.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @EventListener(ApplicationReadyEvent.class)// wykonuje sie przyuruchomieniu aplikacji
//    //@PostMapping("/dodaj")
//    public void InitializeEmployees() {
//        Employee employee1 = new Employee("Jan", "Borys");
//        Employee employee2 = new Employee("Kazimierz", "Jan");
//        Employee employee3 = new Employee("Borys", "Kazimierz");
//        Employee employee4 = new Employee("Bys", "ierz");
//        emlpoyeRepo.save(employee1);
//        emlpoyeRepo.save(employee2);
//        emlpoyeRepo.save(employee3);
//        emlpoyeRepo.save(employee4);
//        employeeList.add(employee1);
//        employeeList.add(employee2);
//        employeeList.add(employee3);
//        employeeList.add(employee4);
//        System.out.println(employeeList);
//    }
}

