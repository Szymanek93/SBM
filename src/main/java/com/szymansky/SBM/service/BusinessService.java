package com.szymansky.SBM.service;

import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.BusinessDTO;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.mapper.BusinessMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public Optional<Business> findBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    public Optional<Business> save(BusinessDTO businessDTO) {
        return Optional.ofNullable(businessDTO)
                .map(businessMapper::fromDTO)
                .map(businessRepository::save);
    }
}
