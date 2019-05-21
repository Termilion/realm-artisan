package com.termilion.realmartisan.controller;

import com.termilion.realmartisan.exception.ResourceNotFoundException;
import com.termilion.realmartisan.model.Group;
import com.termilion.realmartisan.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public List<Group> getAllEthnicity(){
        return groupRepository.findAll();
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") Long ethnicityId) throws ResourceNotFoundException {
        Group group = groupRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + ethnicityId));
        return ResponseEntity.ok().body(group);
    }

    @PostMapping("/groups")
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupRepository.save(group);
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable(value = "id") Long ethnicityId, @Valid @RequestBody Group groupDetails) throws ResourceNotFoundException {
        Group group = groupRepository.findById(ethnicityId).orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + ethnicityId));

        group.setAll(groupDetails);

        final Group updatedGroup = groupRepository.save(group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/groups/{id}")
    public Map<String, Boolean> deleteGroups(@PathVariable(value = "id") Long groupId) throws ResourceNotFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group not found by this id :: " + groupId));
        groupRepository.delete(group);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
