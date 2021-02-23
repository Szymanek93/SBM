package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Entity.EmployeeDTO;
import com.szymansky.SBM.Entity.Owner;
//import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Entity.OwnerDTO;
import com.szymansky.SBM.Repository.OwnerRepository;
import com.szymansky.SBM.mapper.OwnerMapper;
import com.szymansky.SBM.service.OwnerService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/owner")
@AllArgsConstructor

public class OwnerRestController {
    private List<Owner> ownersList;

    private final OwnerRepository ownerRepo;
    private final OwnerMapper ownerMapper;
    private final OwnerService ownerService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Owner> getOwnerById(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        Owner business = ownerRepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + id));
//        return ResponseEntity.ok().body(business);
//    }

    @GetMapping("/{id}")
    public OwnerDTO getOwnerById(@PathVariable Long id) {
        return ownerService.findOwnerById(id)
                .map(ownerMapper::toDTO)
                .orElseThrow(supplyOwnerNotFound(id));
    }


    @GetMapping("")
    public List<Owner> getOwners() {
        return ownerRepo.findAll();
    }


    @PostMapping("")
    public Owner addOwner(@RequestBody OwnerDTO ownerDTO) {
        return ownerService.save(ownerDTO)
                .orElseThrow(supplyOwnerNotSaved());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Owner> updateOwner(@PathVariable(value = "id") Long id, @RequestBody Owner ownerDetails) {
        Owner owner = ownerRepo.findById(id)
                .orElseThrow(supplyOwnerNotFound(id));

        owner.setOwnerName(ownerDetails.getOwnerName());
        owner.setOwnerCity(ownerDetails.getOwnerCity());
        owner.setOwnerPostCode(ownerDetails.getOwnerPostCode());
        owner.setOwnerStreet(ownerDetails.getOwnerStreet());
        owner.setOwnerHouseNumber(ownerDetails.getOwnerHouseNumber());
        owner.setOwnerPhone(ownerDetails.getOwnerPhone());
        //owner.setOwnerBusiness(ownerDetails.getOwnerBusiness());
//        owner.setOwnerBusinessId(ownerDetails.getOwnerBusinessId());

        final Owner updatedOwner = ownerRepo.save(owner);
        return ResponseEntity.ok(updatedOwner);
    }
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public Optional<Owner> updateOwnerDTO(@PathVariable(value = "id") Long id, @RequestBody OwnerDTO ownerDetails) {
//        OwnerDTO ownerDTO = ownerService.findOwnerById(id)
//                .map(ownerMapper::toDTO)
//                .orElseThrow(supplyOwnerNotFound(id));
//
//        ownerDTO.setOwnerId(ownerDetails.getOwnerId());
//        ownerDTO.setOwnerName(ownerDetails.getOwnerName());
//        ownerDTO.setOwnerCity(ownerDetails.getOwnerCity());
//        ownerDTO.setOwnerHouseNumber(ownerDetails.getOwnerHouseNumber());
//        ownerDTO.setOwnerPhone(ownerDetails.getOwnerPhone());
//        ownerDTO.setOwnerPostCode(ownerDetails.getOwnerPostCode());
//        ownerDTO.setOwnerStreet(ownerDetails.getOwnerStreet());
//        return ownerService.save(ownerDTO);
//
//    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable(value = "id") Long id) {
        ownerRepo.deleteById(id);
    }

    private Supplier<ResponseStatusException> supplyOwnerNotFound(Long id) {
        return () -> {
            String message = MessageFormat.format("Owner not found for this id:{0}", id.toString());
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }

    private Supplier<ResponseStatusException> supplyOwnerNotSaved() {
        return () -> {
            String message = "Something Wrong";
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        };
    }

}
