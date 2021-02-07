package com.szymansky.SBM.mapper;


import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Entity.Report;
import com.szymansky.SBM.Entity.ReportDTO;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.Repository.EmployeeRepository;
import com.szymansky.SBM.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class ReportMapper {
    private final ReportRepository reportRepository = null;
    private final BusinessRepository businessRepository = null;
    private final EmployeeRepository employeeRepository = null;

    public ReportDTO toDTO(Report report) {
        return ReportDTO.builder()
                .reportCompleteTasks(report.getCompleteTasks())
                .reportDevelopmentDate(report.getDevelopmentDate())
                .reportWorksDate((report.getWorksDate()))
                .reportOtherTasks(report.getOtherTasks())
                .reportPointsAmount(report.getPointsAmount())
                .businessId(report.getBusiness().getId())
                .employeeId(report.getEmployee().getId())
                .build();
    }

    public Report fromDTO(ReportDTO reportDTO) {
        return Report.builder()
                .completeTasks(reportDTO.getReportCompleteTasks())
                .developmentDate(reportDTO.getReportDevelopmentDate())
                .worksDate(reportDTO.getReportWorksDate())
                .otherTasks(reportDTO.getReportOtherTasks())
                .pointsAmount(reportDTO.getReportPointsAmount())
                .business(getBusinessById(reportDTO.getBusinessId()))
                .employee(getEmployeeById(reportDTO.getEmployeeId()))
                .build();
    }

    private Business getBusinessById(Long businessId) {
        return businessRepository.findById(businessId)
                .orElseThrow(supplyBusinessNotFound(businessId));
    }
    private Employee getEmployeeById (Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(supplyEmployeeNotFound(employeeId));
    }

    private Supplier<ResponseStatusException> supplyEmployeeNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Employee not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    private Supplier<ResponseStatusException> supplyBusinessNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Business not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }
}