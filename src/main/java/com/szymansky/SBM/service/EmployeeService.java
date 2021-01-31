package com.szymansky.SBM.service;


import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Entity.EmployeeDTO;
import com.szymansky.SBM.Repository.EmployeeRepository;
import com.szymansky.SBM.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public Optional<Employee> findEmployeeById(Long id)
    {return employeeRepository.findById(id);}

    public  Optional<Employee> save (EmployeeDTO employeeDTO){
        return Optional.ofNullable(employeeDTO)
                .map(employeeMapper::fromDTO)
                .map(employeeRepository::save);
    }

}
