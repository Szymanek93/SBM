package com.szymansky.SBM.Repository;

import com.szymansky.SBM.Entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
    List<Owner> findAllByOwnerName(String ownerName);
    Optional<Owner> findById(Long id);
}
