package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Entity.BusinessDTO;
//import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.BusinessRepository;
import com.szymansky.SBM.mapper.BusinessMapper;
import com.szymansky.SBM.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping
    public List<Business> getBusinesses() {
        return businessRepository.findAll();
    }

    @PostMapping
    public Business saveBusiness(@RequestBody BusinessDTO businessDTO) {
        return businessService.save(businessDTO)
                .orElseThrow(supplyBusinessNotSaved());
    }
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason ="ajajajaja")
    public class AJException extends Exception{

    }
    private Supplier<ResponseStatusException> supplyBusinessNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }

    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(@PathVariable Long id,
                                                   @RequestBody Business businessDetails)

    {
        Optional<Business> optionalBusiness = businessRepository.findById(id);
        Business business = optionalBusiness.get();


        business.setId(businessDetails.getId());
        business.setOwner(businessDetails.getOwner());
        business.setBusinessType(businessDetails.getBusinessType());
        business.setBusinessName(businessDetails.getBusinessName());
        business.setBusinessCity(businessDetails.getBusinessCity());
        business.setBusinessPostCode(businessDetails.getBusinessPostCode());
        business.setBusinessStreet(businessDetails.getBusinessStreet());
        business.setBusinessHouseNumber(businessDetails.getBusinessHouseNumber());
        business.setBusinessDetails(businessDetails.getBusinessDetails());


        final Business updatedBusiness = businessRepository.save(business);
        return ResponseEntity.ok(updatedBusiness);
    }

    @DeleteMapping("/{id}")
    public void deleteBusiness(@PathVariable(value = "id") Long id)
    {

        businessRepository.deleteById(id);
    }


}
