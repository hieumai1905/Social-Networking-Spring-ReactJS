package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.TypeRelationship;
import com.codegym.model.Relation;

import com.codegym.service.relationService.RelationService;
import com.codegym.service.typeRelationService.RelationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/relationship")
public class RelationshipController {
    private final Long FRIEND = 1l;
    private final Long PENDING = 2l;
    private final Long FOLLOW = 3l;
    private final Long BLOCK = 4l;
    private final Long NO = 0l;
    @Autowired
    RelationService relationshipService;
    @Autowired
    RelationTypeService relationTypeService;

    @PostMapping("/sentFriendRequest/{userId}/{targetId}")
    public ResponseEntity<Relation> sentFriendRequest(@PathVariable Long userId,
                                                          @PathVariable Long targetId) {
        Relation relationship = relationshipService.findByUserAndTarget(userId, targetId);

        if (relationship == null){
            relationship = Relation.builder()
                    .user(AppUser.builder().id(userId).build())
                    .targetUser(AppUser.builder().id(targetId).build())
                    .typeRelationShip(relationTypeService.findById(PENDING))
                    .build();
        } else {
            relationship.setTypeRelationShip(TypeRelationship.builder().id(2l).build());

        }
        relationshipService.save(relationship);
        return new ResponseEntity<>(relationship,HttpStatus.OK);
    }

    @PostMapping("/cancelFriendRequest")
    public ResponseEntity<Relation> cancelFriendRequest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/acceptFriendRequest")
    public ResponseEntity<Relation> acceptFriendRequest() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/{targetId}")
    public ResponseEntity<Relation> getRelationById(@PathVariable Long userId,
                                                        @PathVariable Long targetId) {
        Relation relationship = relationshipService.findByUserAndTarget(userId, targetId);
        return new ResponseEntity<>(relationship, HttpStatus.OK);
    }

    @PutMapping("/{relationId}/{typeId}")
    public ResponseEntity<Relation> modifyRelation(@PathVariable Long relationId,
                                                       @PathVariable Long typeId) {
        if (typeId == 0) relationshipService.delete(relationId);

        relationshipService.modifyRelation(relationId, typeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Relation>> getAllFriendRequests(@PathVariable Long userId,
                                                                   @RequestParam("relation") Long relation) {
        List<Relation> relationships = relationshipService.findAllByUserId(userId, relation);
        return new ResponseEntity<>(relationships, HttpStatus.OK);
    }


}
