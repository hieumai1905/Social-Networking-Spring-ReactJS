package com.codegym.service.typeRelationService;

import com.codegym.model.TypeRelationship;
import com.codegym.repository.IRelationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelationTypeService implements IRelationTypeService {
    @Autowired
    private IRelationTypeRepo relationTypeRepo;

    @Override
    public List<TypeRelationship> findAll() {
        return relationTypeRepo.findAll();
    }

    @Override
    public boolean save(TypeRelationship relationType) {
        return relationTypeRepo.save(relationType) != null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public TypeRelationship findById(Long id) {
        Optional<TypeRelationship> relationshipOptional = relationTypeRepo.findById(id);
        if (relationshipOptional.isEmpty()) {
            int Tid = Math.toIntExact(id);
            String name = "";
            switch (Tid) {
                case 0:
                    name = "NO";
                    break;
                case 1:
                    name = "FRIEND";
                    break;
                case 2:
                    name = "PENDING";
                    break;
                case 3:
                    name = "WAIT_ACCEPT";
                    break;
                case 4:
                    name = "BLOCK";
                    break;
                case 5:
                    name = "FOLLOW";
                    break;
            }
            save(TypeRelationship.builder().id(id).typeRelationshipName(name).build());
            relationshipOptional  = relationTypeRepo.findById(id);
        }
        return relationshipOptional.orElse(null);
    }
}
