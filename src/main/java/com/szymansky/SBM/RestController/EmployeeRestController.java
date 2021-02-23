package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.Employee;
//import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Entity.EmployeeDTO;
import com.szymansky.SBM.Repository.EmployeeRepository;
import com.szymansky.SBM.mapper.EmployeeMapper;
import com.szymansky.SBM.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeRestController {

    private List<Employee> employeeList;
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepo;


//    @GetMapping("/{id}")
//    public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") Long id)
//           {
//        Employee employee = employeeRepo.findById(id)
//                .orElseThrow(supplyEmployeeNotFound(id));
//        return ResponseEntity.ok().body(employee);
//    }

    //Mapperversion

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeesById(@PathVariable Long id) {
        String message = "kdsoadksaodkasodk";
        return employeeService.findEmployeeById(id)
                .map(employeeMapper::toDTO)
//                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,message));
                .orElseThrow(supplyEmployeeNotFound(id));
    }

    //wyszukiwanie wszystkich obiektów
    @GetMapping("")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployee();
    }


    //add new record
    @PostMapping("")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        return employeeService.save(employeeDTO)
                .orElseThrow(supplyEmployeeNotSaved());
    }

//    @PostMapping("")
//    public Employee addEmployee(@RequestBody Employee employee) {
//        return employeeRepo.save(employee);
//    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody Employee employeeDetails){
//        Employee employee = employeeRepo.findById(id)
//                .orElseThrow(supplyEmployeeNotFound(id));
//
//        employee.setId(employeeDetails.getId());
//        employee.setName(employeeDetails.getName());
//        employee.setLastName(employeeDetails.getLastName());
//        final Employee updatedEmployee = employeeRepo.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }
    public class EmployeeNotFoundExceptions extends RuntimeException {
        public EmployeeNotFoundExceptions(long id) {
            super("Could not find employee: " + id);
        }
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<Employee> updateEmployeeDTO(@PathVariable(value = "id") Long id, @RequestBody EmployeeDTO employeeDetails) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(id)
                .map(employeeMapper::toDTO)
                .orElseThrow(supplyEmployeeNotFound(id));

        employeeDTO.setEmployeeId(employeeDetails.getEmployeeId());
        employeeDTO.setEmployeeName(employeeDetails.getEmployeeName());
        employeeDTO.setEmployeeLastName(employeeDetails.getEmployeeLastName());
        return employeeService.save(employeeDTO);

    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long id) {
        employeeRepo.deleteById(id);
    }


    public Supplier<ResponseStatusException> supplyEmployeeNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Employee not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    private Supplier<ResponseStatusException> supplyEmployeeNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }
//    @EventListener(ApplicationReadyEvent.class)// wykonuje sie przyuruchomieniu aplikacji
//    //@PostMapping("/dodaj")
//    public void InitializeEmployees() {
//        Employee employee1 = new Employee("Jan", "Borys");
//        Employee employee2 = new Employee("Kazimierz", "Długi");
//        Employee employee3 = new Employee("Borys", "Prosty");
//        Employee employee4 = new Employee("Jan", "Nowak");
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

