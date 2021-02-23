package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.Report;
import com.szymansky.SBM.Entity.ReportDTO;
//import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.ReportRepository;
import com.szymansky.SBM.mapper.ReportMapper;
import com.szymansky.SBM.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Converter;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportRestController {

    private List<Report> reportList;

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ReportService reportService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Report> getWorkById(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        Report work = reportRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("work not found for this id :: " + id));
//        return ResponseEntity.ok().body(work);
//    }

    @GetMapping("/{id}")
    public ReportDTO getReportById(@PathVariable Long id) {
        return reportService.findReportById(id)
                .map(reportMapper::toDTO)
                .orElseThrow(supplyReportNotFound(id));
    }

    private Supplier<ResponseStatusException> supplyReportNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Report not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    //wyszukiwanie wszystkich obiektów
    @GetMapping
        public List<Report> getReports() {
        return reportService.findAllReport();
    }
    //add new record
    @PostMapping("")
    public Report addReport(@RequestBody ReportDTO reportDTO) {
        return reportService.save(reportDTO)
                .orElseThrow(supplyReportNotSaved());
    }

    private Supplier<ResponseStatusException> supplyReportNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }

//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Report> updateReport(@PathVariable(value = "id") Long id, @RequestBody Report reportDetails) {
//
//        Optional<Report> optionalReport = reportService.findReportById(id);
//        Report report = optionalReport.get();
//
//        report.setId(reportDetails.getId());
//        report.setEmployee(reportDetails.getEmployee());
//        report.setBusiness(reportDetails.getBusiness());
//        report.setCompleteTasks(report.getCompleteTasks());
//        report.setDevelopmentDate(report.getDevelopmentDate());
//        report.setOtherTasks((report.getOtherTasks()));
//        report.setPointsAmount((report.getPointsAmount()));
//        report.setWorksDate((report.getWorksDate()));
//
//
//        final Report updatedReport = reportRepository.save(report);
//        return ResponseEntity.ok(updatedReport);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Optional<Report> updateReportDTO(@PathVariable(value = "id") Long id, @RequestBody ReportDTO reportDetails) {
        ReportDTO reportDTO = reportService.findReportById(id)
                .map(reportMapper::toDTO)
                .orElseThrow(supplyReportNotFound(id));
//        Optional<Report> optionalReport = reportService.findReportById(id);
//        Report report = optionalReport.get();

        reportDTO.setReportId(reportDetails.getReportId());
//        reportDTO.setEmployeeId(reportDetails.getEmployeeId());
//        reportDTO.setBusinessId(reportDetails.getBusinessId());
        reportDTO.setReportCompletedTasks(reportDetails.getReportCompletedTasks());
        reportDTO.setReportDevelopmentDate(reportDetails.getReportDevelopmentDate());
        reportDTO.setReportOtherTasks(reportDetails.getReportOtherTasks());
        reportDTO.setReportPointsAmount((reportDetails.getReportPointsAmount()));
        reportDTO.setReportWorksDate((reportDetails.getReportWorksDate()));
        reportDTO.setBusinessName(reportDetails.getBusinessName());
        reportDTO.setEmployeeName(reportDetails.getEmployeeName());
        reportDTO.setEmployeeLastName(reportDetails.getEmployeeLastName());

        return reportService.save(reportDTO);
//        final Report updatedReport = reportRepository.save(report);
//        return ResponseEntity.ok(updatedReport);
    }
        @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable(value = "id") Long id) {
        reportRepository.deleteById(id);
    }

}
