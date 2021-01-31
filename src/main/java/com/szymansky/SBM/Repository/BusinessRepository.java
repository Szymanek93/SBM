package com.szymansky.SBM.Repository;

import com.szymansky.SBM.Entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Long> {

    List<Business> findAllByBusinessName(String businessName);
    Optional<Business> findById(Long id);
    List<Business> findAll();
   }

