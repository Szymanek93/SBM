package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeRestController {

    private List<Employee> employeeList;

    private final EmployeeRepository emlpoyeRepo;



    @GetMapping("/{id}")
    public Employee getEmployees(@PathVariable Long id) {
        return employeeList.stream()
                .filter(employee -> employee.getId() == id).findFirst().get();
    }
    //wyszukiwanie wszystkich obiektów
    @GetMapping("/findAll")
    public List<Employee> getEmployees() {
        List<Employee> employeeList=emlpoyeRepo.findAll();
        return employeeList;
    }

    //dodawanie nowego obiektu
    @ResponseBody
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        Optional<Employee> employee1 = emlpoyeRepo.findById((employee.getId()));
        if (employee1.isPresent()!=true){
            return emlpoyeRepo.save(employee);
        }
        else
            return null;

    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        getEmployees(id);
        emlpoyeRepo.deleteById(id);
        System.out.print("Pomyślnie usunięto");
        System.out.println(id);
        getEmployees();
    }

    @EventListener(ApplicationReadyEvent.class)// wykonuje sie przyuruchomieniu aplikacji
    //@PostMapping("/dodaj")
    public void InitializeEmployees() {
        System.out.println("Adding employees");
        Employee employee1 = new Employee("Jan", "Borys");
        Employee employee2 = new Employee( "Kazimierz", "Jan");
        Employee employee3 = new Employee( "Borys", "Kazimierz");
        Employee employee4 = new Employee( "Bys", "ierz");

        emlpoyeRepo.save(employee1);
        emlpoyeRepo.save(employee2);
        emlpoyeRepo.save(employee3);
        emlpoyeRepo.save(employee4);
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);
        System.out.println("dodano");
        System.out.println(employeeList);
    }
}

