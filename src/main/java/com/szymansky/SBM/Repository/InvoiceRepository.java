package com.szymansky.SBM.Repository;


import com.szymansky.SBM.Entity.Invoice;
import com.szymansky.SBM.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
//    Optional<Invoice> findBytId(Long id);
//    List<Invoice> findAll();
}
