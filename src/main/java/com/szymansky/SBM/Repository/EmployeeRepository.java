package com.szymansky.SBM.Repository;

import com.szymansky.SBM.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByName(String name);
    Optional<Employee> findById(Long id);
}
