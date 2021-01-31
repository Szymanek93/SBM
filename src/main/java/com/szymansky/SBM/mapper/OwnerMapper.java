package com.szymansky.SBM.mapper;


import com.szymansky.SBM.Entity.Owner;
import com.szymansky.SBM.Entity.OwnerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {

    public OwnerDTO toDTO (Owner owner) {
        return OwnerDTO.builder()
                .ownerCity(owner.getOwnerCity())
                .ownerHouseNumber(owner.getOwnerHouseNumber())
                .ownerName(owner.getOwnerName())
                .ownerPhone(owner.getOwnerPhone())
                .ownerPostCode(owner.getOwnerPostCode())
                .ownerStreet(owner.getOwnerStreet())
                .build();
    }
    public Owner fromDTO(OwnerDTO ownerDTO){
        return Owner.builder()
                .ownerCity(ownerDTO.getOwnerCity())
                .ownerHouseNumber(ownerDTO.getOwnerHouseNumber())
                .ownerName(ownerDTO.getOwnerName())
                .ownerPhone((ownerDTO.getOwnerPhone()))
                .ownerPostCode((ownerDTO.getOwnerPostCode()))
                .ownerStreet((ownerDTO.getOwnerStreet()))
                .build();
    }
}
