package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Character;
import com.termilion.realmartisan.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/characters")
    public List<Character> getAllCharacters(){
        return characterRepository.findAll();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable(value = "id") Long characterId) throws ResourceNotFoundException {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Character not found for this id :: " + characterId));
        return ResponseEntity.ok().body(character);
    }

    @PostMapping("characters")
    public Character createCharacter(@Valid @RequestBody Character character) {
        return characterRepository.save(character);
    }

    @PutMapping("/character/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable(value = "id") Long characterId, @Valid @RequestBody Character characterDetails) throws ResourceNotFoundException {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Character not found for this id :: " + characterId));

        character.setAll(characterDetails);

        final Character updatedCharacter = characterRepository.save(character);
        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping("/characters/{id}")
    public Map<String, Boolean> deleteCharacter(@PathVariable(value = "id") Long characterId) throws ResourceNotFoundException {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new ResourceNotFoundException("Character not found by this id :: " + characterId));
        characterRepository.delete(character);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
