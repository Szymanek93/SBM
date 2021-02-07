package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.BusinessDTO;
//import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.mapper.BusinessMapper;
import com.szymansky.SBM.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessRestController {


    private final BusinessService businessService;
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

//    @GetMapping("/{id}")
//    public BusinessDTO getBusinessById(@PathVariable Long id) {
//        return businessService.findBusinessById(id)
//                .map(businessMapper::toDTO)
//                .orElseThrow(supplyBusinessNotFound(id));
//    }

    @GetMapping("/{id}")
    public BusinessDTO getBusinessById(@PathVariable Long id) {
        return businessService.findBusinessById(id)
                .map(businessMapper::toDTO)
                .orElseThrow(supplyBusinessNotFound(id));
    }

    private Supplier<ResponseStatusException> supplyBusinessNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Business not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

//    @GetMapping
//    public List<Business> getBusinesses() {
//        return businessRepository.findAll();
//    }

    @GetMapping
    public List<Business> getBusinesses() {
        return businessService.findAllBusiness();
    }

    @PostMapping
    public Business saveBusiness(@RequestBody BusinessDTO businessDTO) {
        return businessService.save(businessDTO)
                .orElseThrow(supplyBusinessNotSaved());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "ajajajaja")
    public class AJException extends Exception {

    }

    private Supplier<ResponseStatusException> supplyBusinessNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }

    //    @PutMapping("/{id}")
//    public ResponseEntity<Business> updateBusiness(@PathVariable Long id,@RequestBody Business businessDetails) {
//        Optional<Business> optionalBusiness = businessService.findBusinessById(id);
//        Business business = optionalBusiness.get();
////zmieniony z repo na service
//
//        business.setId(businessDetails.getId());
//        business.setOwner(businessDetails.getOwner());
//        business.setBusinessType(businessDetails.getBusinessType());
//        business.setBusinessName(businessDetails.getBusinessName());
//        business.setBusinessCity(businessDetails.getBusinessCity());
//        business.setBusinessPostCode(businessDetails.getBusinessPostCode());
//        business.setBusinessStreet(businessDetails.getBusinessStreet());
//        business.setBusinessHouseNumber(businessDetails.getBusinessHouseNumber());
//        business.setBusinessDetails(businessDetails.getBusinessDetails());
//
//
//        final Business updatedBusiness = businessRepository.save(business);
//        return ResponseEntity.ok(updatedBusiness);
//    }
    @PutMapping("/{id}")
    public Optional<Business> updateBusiness(@PathVariable Long id, @RequestBody BusinessDTO businessDetails) {
        BusinessDTO businessDTO = businessService.findBusinessById(id)
                .map(businessMapper::toDTO)
                .orElseThrow(supplyBusinessNotFound(id));

        businessDTO.setBusinessId(businessDetails.getBusinessId());
        businessDTO.setOwnerId(businessDetails.getOwnerId());
        businessDTO.setBusinessType(businessDetails.getBusinessType());
        businessDTO.setBusinessName(businessDetails.getBusinessName());
        businessDTO.setBusinessCity(businessDetails.getBusinessCity());
        businessDTO.setBusinessPostCode(businessDetails.getBusinessPostCode());
        businessDTO.setBusinessStreet(businessDetails.getBusinessStreet());
        businessDTO.setBusinessHouseNumber(businessDetails.getBusinessHouseNumber());
        businessDTO.setBusinessDetails(businessDetails.getBusinessDetails());

        return businessService.save(businessDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable(value = "id") Long id) {

        businessRepository.deleteById(id);
    }


}
