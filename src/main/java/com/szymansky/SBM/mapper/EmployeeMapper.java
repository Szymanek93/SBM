package com.szymansky.SBM.mapper;


import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Entity.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee){
        return EmployeeDTO.builder()
                .employeeName(employee.getName())
                .employeeLastName(employee.getLastName())
                .build();
    }
    public Employee fromDTO(EmployeeDTO employeeDTO){
        return Employee.builder()
                .name(employeeDTO.getEmployeeName())
                .lastName(employeeDTO.getEmployeeLastName())
                .build();
    }

}