package com.szymansky.SBM.service;


import com.szymansky.SBM.Entity.Owner;
import com.szymansky.SBM.Entity.OwnerDTO;
import com.szymansky.SBM.Repository.OwnerRepository;
import com.szymansky.SBM.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public Optional<Owner> findOwnerById(Long id) {
        return ownerRepository.findById(id);
    }
    public Optional<Owner> save (OwnerDTO ownerDTO){
        return Optional.ofNullable(ownerDTO)
                .map(ownerMapper::fromDTO)
                .map(ownerRepository::save);


}
}
