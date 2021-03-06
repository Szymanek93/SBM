package com.szymansky.SBM.service;


import com.szymansky.SBM.Entity.Report;
import com.szymansky.SBM.Entity.ReportDTO;
import com.szymansky.SBM.Repository.ReportRepository;
import com.szymansky.SBM.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public Optional<Report> findReportById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> findAllReport(){
        return reportRepository.findAll();
    }
    public Optional<Report> save(ReportDTO reportDTO) {
        return Optional.ofNullable(reportDTO)
                .map(reportMapper::fromDTO)
                .map(reportRepository::save);
    }

//    @Override
//    public Page<ReportDTO> getAllReports (){
//        List<Report> reports = reportRepository.findAll();
//        Pageable pageable;
//        Page <Report> reportPage = new PageImpl<Report>(reports,pageable, reports.size());
//
//
//    }
}
