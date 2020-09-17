package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.Owner;
import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/owner")
@AllArgsConstructor

public class OwnerRestController {
    private List<Owner> ownersList;

    private final OwnerRepository ownerRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Owner business = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + id));
        return ResponseEntity.ok().body(business);
    }

    @GetMapping("")
    public List<Owner> getOwners() {
        return ownerRepo.findAll();
    }

    @PostMapping("")
    public Owner addOwner(@RequestBody Owner employee) {
        return ownerRepo.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Owner> updateOwner(@PathVariable(value = "id") Long id, @RequestBody Owner ownerDetails)
            throws ResourceNotFoundException {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + id));

        owner.setOwnerName(ownerDetails.getOwnerName());
        owner.setOwnerCity(ownerDetails.getOwnerCity());
        owner.setOwnerPostCode(ownerDetails.getOwnerPostCode());
        owner.setOwnerStreet(ownerDetails.getOwnerStreet());
        owner.setOwnerHouseNumber(ownerDetails.getOwnerHouseNumber());
        owner.setOwnerPhone(ownerDetails.getOwnerPhone());
        owner.setOwnerBusiness(ownerDetails.getOwnerBusiness());
        owner.setOwnerBusinessId(ownerDetails.getOwnerBusinessId());

        final Owner updatedOwner = ownerRepo.save(owner);
        return ResponseEntity.ok(updatedOwner);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOwner(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id::" + id));

        ownerRepo.delete(owner);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
