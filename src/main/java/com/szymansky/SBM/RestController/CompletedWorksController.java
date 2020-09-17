package com.szymansky.SBM.RestController;

import com.szymansky.SBM.Entity.CompletedWorks;
import com.szymansky.SBM.Entity.Employee;
import com.szymansky.SBM.Exception.ResourceNotFoundException;
import com.szymansky.SBM.Repository.CompleteWorksRepository;
import com.szymansky.SBM.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/works")
@AllArgsConstructor
public class CompletedWorksController {

    private List<CompletedWorks> worksList;

    private final CompleteWorksRepository worksRepo;

    @GetMapping("/{id}")
    public ResponseEntity<CompletedWorks> getWorkById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        CompletedWorks work = worksRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("work not found for this id :: " + id));
        return ResponseEntity.ok().body(work);
    }

    //wyszukiwanie wszystkich obiektów
    @GetMapping("")
    public List<CompletedWorks> getWorks() {
        return worksRepo.findAll();
    }

    //add new record
    @PostMapping("")
    public CompletedWorks addWork(@RequestBody CompletedWorks work) {
        return worksRepo.save(work);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CompletedWorks> updateWork (@PathVariable(value = "id") Long id, @RequestBody CompletedWorks workDetails)
            throws ResourceNotFoundException {
        CompletedWorks work= worksRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("work not found for this id :: " + id));

        work.setId(workDetails.getId());

        final CompletedWorks updatedWork = worksRepo.save(work);
        return ResponseEntity.ok(updatedWork);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        CompletedWorks work = worksRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("worknot found for this id::" + id));

        worksRepo.delete(work);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
