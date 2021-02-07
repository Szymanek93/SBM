//package com.szymansky.SBM.RestController;
//
//import com.szymansky.SBM.Entity.Report;
//import com.szymansky.SBM.Entity.ReportDTO;
////import com.szymansky.SBM.Exception.ResourceNotFoundException;
//import com.szymansky.SBM.Repository.ReportRepository;
//import com.szymansky.SBM.mapper.ReportMapper;
//import com.szymansky.SBM.service.ReportService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Supplier;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
//@RequestMapping("/works")
//@AllArgsConstructor
//public class ReportController {
//
//    private List<Report> worksList;
//
//    private final ReportRepository reportRepository;
//    private final ReportMapper reportMapper;
//    private final ReportService reportService;
////
////    @GetMapping("/{id}")
////    public ResponseEntity<Report> getWorkById(@PathVariable(value = "id") Long id)
////            throws ResourceNotFoundException {
////        Report work = reportRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("work not found for this id :: " + id));
////        return ResponseEntity.ok().body(work);
////    }
//
//    @GetMapping("/{id}")
//    public ReportDTO getReportById (@PathVariable Long id){
//        return reportService.findReportById(id)
//                .map(reportMapper::toDTO)
//                .orElseThrow(supplyReportNotFound(id));
//    }
//    private Supplier<ResponseStatusException> supplyReportNotFound(Long id) {
//        return () -> {
//            String message = MessageFormat.format("Report not found for this id:{0}", id.toString());
//            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
//        };
//    }
//    //wyszukiwanie wszystkich obiektów
//    @GetMapping("")
//    public List<Report> getReports() {
//        return reportRepository.findAll();
//    }
//
//    //add new record
//    @PostMapping("")
//    public Report addReport(@RequestBody ReportDTO reportDTO) {
//        return reportService.save(reportDTO)
//                .orElseThrow(supplyReportNotSaved());
//    }
//
//    private Supplier<ResponseStatusException> supplyReportNotSaved() {
//        return () -> {
//            String message = "Something Wrong";
//            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
//        };
//    }
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Report> updateReport (@PathVariable(value = "id") Long id, @RequestBody Report workDetails)
//            throws ResourceNotFoundException {
//        Report work= reportRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("work not found for this id :: " + id));
//
//        work.setId(workDetails.getId());
//
//        final Report updatedWork = reportRepository.save(work);
//        return ResponseEntity.ok(updatedWork);
//    }
//
//    @DeleteMapping("/{id}")
//    public Map<String, Boolean> deleteReport(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        Report report = reportRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("worknot found for this id::" + id));
//
//        reportRepository.delete(report);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
//}
