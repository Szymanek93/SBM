package com.szymansky.SBM.mapper;

import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.BusinessDTO;
import com.szymansky.SBM.Entity.Owner;
import com.szymansky.SBM.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class BusinessMapper {

    private final OwnerRepository ownerRepository;

    public BusinessDTO toDTO(Business business){
        return BusinessDTO.builder()
                .businessCity(business.getBusinessCity())
                .businessDetails(business.getBusinessDetails())
                .businessHouseNumber(business.getBusinessHouseNumber())
                .businessName(business.getBusinessName())
                .businessPostCode(business.getBusinessPostCode())
                .businessStreet(business.getBusinessStreet())
                .businessType(business.getBusinessType())
                .ownerId(business.getOwner().getId())
                .businessId(business.getId())
                .build();
    }

    public Business fromDTO(BusinessDTO businessDTO){
        return Business.builder()
                .businessCity(businessDTO.getBusinessCity())
                .businessDetails(businessDTO.getBusinessDetails())
                .businessHouseNumber(businessDTO.getBusinessHouseNumber())
                .businessName(businessDTO.getBusinessName())
                .businessPostCode(businessDTO.getBusinessPostCode())
                .businessStreet(businessDTO.getBusinessStreet())
                .businessType(businessDTO.getBusinessType())
                .owner(getOwnerById(businessDTO.getOwnerId()))
                .id(businessDTO.getBusinessId())
                .build();
    }
 //Do klasy ownerService
    private Owner getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId)
                .orElseThrow(supplyOwnerNotFound(ownerId));
    }

    private Supplier<ResponseStatusException> supplyOwnerNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Owner not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }
}
