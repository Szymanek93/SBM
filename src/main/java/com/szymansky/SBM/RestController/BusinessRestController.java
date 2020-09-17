package com.szymansky.SBM.RestController;


import com.szymansky.SBM.Entity.Business;
import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessRestController {

    private List<Business> businessList;

    private final BusinessRepository businessRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business not found for this id :: " + id));
        return ResponseEntity.ok().body(business);
    }

    @GetMapping("")
    public List<Business> getBusinesses() {
        return businessRepo.findAll();
    }

    @PostMapping("")
    public Business addBusiness(@RequestBody Business employee) {
        return businessRepo.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Business> updateBusiness(@PathVariable(value = "id") Long id, @RequestBody Business businessDetails)
            throws ResourceNotFoundException {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business not found for this id :: " + id));

        business.setId(businessDetails.getId());
        business.setBusinessOwner(businessDetails.getBusinessOwner());
        business.setBusinessType(businessDetails.getBusinessType());
        business.setBusinessName(businessDetails.getBusinessName());
        business.setBusinessCity(businessDetails.getBusinessCity());
        business.setBusinessPostCode(businessDetails.getBusinessPostCode());
        business.setBusinessStreet(businessDetails.getBusinessStreet());
        business.setBusinessHouseNumber(businessDetails.getBusinessHouseNumber());
        business.setBusinessDetails(businessDetails.getBusinessDetails());


        final Business updatedBusiness = businessRepo.save(business);
        return ResponseEntity.ok(updatedBusiness);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteBusiness(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Business business = businessRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Business not found for this id::" + id));

        businessRepo.delete(business);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
