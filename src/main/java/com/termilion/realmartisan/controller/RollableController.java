package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Character;
import com.termilion.realmartisan.model.Distribution;
import com.termilion.realmartisan.model.Ethnicity;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.EthnicityRepository;
import com.termilion.realmartisan.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RollableController {
    @Autowired
    private EthnicityRepository ethnicityRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/roll/{id}/{name}")
    public ResponseEntity<Character> rollCharacter(@PathVariable(value = "id") Long regionId, @PathVariable(value = "name") String name) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResourceNotFoundException("Region not found for this id :: " + regionId));
        long ethId = Long.parseLong(new Distribution(region.getEthnicities()).roll());
        Ethnicity ethnicity = ethnicityRepository.findById(ethId)
                .orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found for this id :: " + ethId));
        Character character = region.rollCharacter(name, ethnicity);
        characterRepository.save(character);
        return ResponseEntity.ok().body(character);
    }

}
