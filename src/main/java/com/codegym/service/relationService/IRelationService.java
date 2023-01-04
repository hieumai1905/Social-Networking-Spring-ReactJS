package com.codegym.service.relationService;

import com.codegym.model.Relation;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IRelationService extends IGeneralService<Relation> {
    Relation findByUserAndTarget(Long userId, Long targetId);
    List<Relation> findAllByUserId(Long userId, Long relationTypeId);
    void modifyRelation(Long relationId, Long typeId);
}
