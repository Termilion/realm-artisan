package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Character;
import com.termilion.realmartisan.model.Ethnicity;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.EthnicityRepository;
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

    @GetMapping("/roll/{id}/{name}/{profession}")
    public ResponseEntity<Character> rollCharacter(@PathVariable(value = "id") Long ethnicityId, @PathVariable(value = "name") String name, @PathVariable(value = "profession") String profession) throws ResourceNotFoundException {
        Ethnicity ethnicity = ethnicityRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Ethnicity not found for this id :: " + ethnicityId));
        Character character = ethnicity.rollCharacter(name, profession, "1");
        characterRepository.save(character);
        return ResponseEntity.ok().body(character);
    }

}
