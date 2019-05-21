package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Character;
import com.termilion.realmartisan.model.Distribution;
import com.termilion.realmartisan.model.Group;
import com.termilion.realmartisan.model.Region;
import com.termilion.realmartisan.repository.CharacterRepository;
import com.termilion.realmartisan.repository.GroupRepository;
import com.termilion.realmartisan.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RollableController {
    @Autowired
    private GroupRepository ethnicityRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/roll/{id}/{name}")
    public ResponseEntity<Character> rollCharacter(@PathVariable(value = "id") Long regionId, @PathVariable(value = "name") String name) throws ResourceNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new ResourceNotFoundException("Region not found for this id :: " + regionId));
        long ethId = Long.parseLong(new Distribution(region.getEthnicities()).roll());
        Group group = ethnicityRepository.findById(ethId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + ethId));
        Character character = region.rollCharacter(name, group);
        characterRepository.save(character);
        return ResponseEntity.ok().body(character);
    }

    @GetMapping("/defaults")
    public void loadDefaults(){
        Group default_human = new Group();

        try {
            JsonReader reader = Json.createReader(
                    new FileReader(new File(
                            "/home/termi/IdeaProjects/realm-artisan/src/main/resources/default_objects.json"
                    )));
        JsonObject object = reader.readObject().getJsonObject("objects");
        JsonObject ageDist = object.getJsonObject("age");
        JsonObject sizeDist = object.getJsonObject("size");
        JsonObject skinDist = object.getJsonObject("skinTone");
        JsonObject hairDist = object.getJsonObject("hairTone");
        JsonObject eyeDist = object.getJsonObject("eyeTone");
        JsonObject professions = object.getJsonObject("professions");

        String[] features = new String[] {"curly hair", "muscular", "overweight", "charismatic", "rich", "pierced", "tattooed", "intelligent", "bearded"};

        default_human.setName("Human");
        default_human.setDescription("Default Human");
        default_human.setAge(ageDist.toString());
        default_human.setSize(sizeDist.toString());
        default_human.setSkinTones(skinDist.toString());
        default_human.setHairTones(hairDist.toString());
        default_human.setEyeTones(eyeDist.toString());
        default_human.setFeatures(features);

        ethnicityRepository.save(default_human);

        List<String> groupIds = new ArrayList<>();
        ethnicityRepository.findAll().forEach(groups -> {
            groupIds.add(String.format("%s", groups.getId()));
        });
        Distribution ethnicities = new Distribution(groupIds);

        Region region = new Region();
        region.setName("Alessa");
        region.setEthnicities(ethnicities.toJson());
        region.setProfessions(professions.toString());
        regionRepository.save(region);

        rollCharacter(1l, "Default");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
