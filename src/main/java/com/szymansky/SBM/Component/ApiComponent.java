package com.szymansky.SBM.Component;

import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Component
//@NoArgsConstructor
//@AllArgsConstructor
public class ApiComponent {

    private final EmployeeRepository emlpoyeRepo;

    @Autowired
    public ApiComponent(EmployeeRepository emlpoyeRepo){this.emlpoyeRepo=emlpoyeRepo; }

//public void ApiReadyExecute(){
//    InitializeEmployees();
//}
//////Dodaje startową bazędanych pracowników
////private void InitializeEmployees(){
////        System.out.println("Adding employees");
////        Employee employee1 = new Employee("Jan","Borys");
////        Employee employee2 = new Employee("Kazimierz","Jan");
////        Employee employee3 = new Employee("Borys","Kazimierz");
////        emlpoyeRepo.save(employee1);
////        emlpoyeRepo.save(employee2);
////        emlpoyeRepo.save(employee3);
////}


}
