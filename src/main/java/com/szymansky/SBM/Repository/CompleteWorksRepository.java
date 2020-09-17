package com.szymansky.SBM.Repository;

import com.szymansky.SBM.Entity.CompletedWorks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompleteWorksRepository extends JpaRepository<CompletedWorks, Long > {
    Optional<CompletedWorks>findById(Long id);
}
