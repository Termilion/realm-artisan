package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @GetMapping("/regions/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable(value = "id") Long regionId) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResourceNotFoundException("Region not found for this id :: " + regionId));
        return ResponseEntity.ok().body(region);
    }

    @PostMapping("regions")
    public Region createRegion(@Valid @RequestBody Region region) {
        return regionRepository.save(region);
    }

    @PutMapping("/region/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable(value = "id") Long regionId, @Valid @RequestBody Region regionDetails) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResourceNotFoundException("Region not found for this id :: " + regionId));

        region.setAll(regionDetails);

        final Region updatedRegion = regionRepository.save(region);
        return ResponseEntity.ok(updatedRegion);
    }

    @DeleteMapping("/regions/{id}")
    public Map<String, Boolean> deleteRegion(@PathVariable(value = "id") Long regionId) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResourceNotFoundException("Region not found by this id :: " + regionId));
        regionRepository.delete(region);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
