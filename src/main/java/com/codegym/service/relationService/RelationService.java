package com.codegym.service.relationService;

import com.codegym.model.Relation;
import com.codegym.repository.IRelationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationService implements IRelationService {
    @Autowired
    IRelationRepo relationshipRepo;

    @Override
    public List<Relation> findAll() {
        return relationshipRepo.findAll();
    }

    @Override
    public boolean save(Relation relationship) {
        return relationshipRepo.save(relationship) != null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Relation findById(Long id) {
        return null;
    }

    /**
     * @param userId
     * @param targetId
     * @return Relation between record
     * null if not found
     */
    @Override
    public Relation findByUserAndTarget(Long userId, Long targetId) {
        Relation relationship = null;
        try{
            relationship = relationshipRepo.findRelationshipByUserAndTargetUser(userId, targetId).orElse(null);
        }catch (Exception e){
            System.out.println("sss"+ e);
        }
        return relationship;
    }

    @Override
    public List<Relation> findAllByUserId(Long userId, Long relationTypeId) {
        return relationshipRepo.findAllByUserAndTypeRelationShip_IdLike(userId, relationTypeId);
    }


    @Override
    public void modifyRelation(Long r, Long t) {
        relationshipRepo.modifyRelationType(r, t);
    }
}
