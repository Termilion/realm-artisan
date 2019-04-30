package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Ethnicity;
import com.termilion.realmartisan.repository.EthnicityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EthnicityController {
    @Autowired
    private EthnicityRepository ethnicityRepository;

    @GetMapping("/eth")
    public List<Ethnicity> getAllEthnicity(){
        return ethnicityRepository.findAll();
    }

    @GetMapping("/ethnicitys/{id}")
    public ResponseEntity<Ethnicity> getEthnicityById(@PathVariable(value = "id") Long ethnicityId) throws ResourceNotFoundException {
        Ethnicity ethnicity = ethnicityRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found for this id :: " + ethnicityId));
        return ResponseEntity.ok().body(ethnicity);
    }

    @PostMapping("ethnicitys")
    public Ethnicity createEthnicity(@Valid @RequestBody Ethnicity ethnicity) {
        return ethnicityRepository.save(ethnicity);
    }

    @PutMapping("/ethnicity/{id}")
    public ResponseEntity<Ethnicity> updateEthnicity(@PathVariable(value = "id") Long ethnicityId, @Valid @RequestBody Ethnicity ethnicityDetails) throws ResourceNotFoundException {
        Ethnicity ethnicity = ethnicityRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found for this id :: " + ethnicityId));

        ethnicity.setAll(ethnicityDetails);

        final Ethnicity updatedEthnicity = ethnicityRepository.save(ethnicity);
        return ResponseEntity.ok(updatedEthnicity);
    }

    @DeleteMapping("/ethnicitys/{id}")
    public Map<String, Boolean> deleteEthnicity(@PathVariable(value = "id") Long ethnicityId) throws ResourceNotFoundException {
        Ethnicity ethnicity = ethnicityRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found by this id :: " + ethnicityId));
        ethnicityRepository.delete(ethnicity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
