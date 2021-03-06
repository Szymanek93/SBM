package com.szymansky.SBM.Repository;

import com.szymansky.SBM.Entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



public interface ReportRepository extends JpaRepository<Report, Long > {
    Optional<Report> findById(Long id);
    List<Report> findAll();

}
