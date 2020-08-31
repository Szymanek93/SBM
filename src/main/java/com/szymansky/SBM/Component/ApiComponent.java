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

}
