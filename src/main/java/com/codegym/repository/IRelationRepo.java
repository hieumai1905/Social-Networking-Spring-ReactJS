package com.codegym.repository;

import com.codegym.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IRelationRepo extends JpaRepository<Relation, Long> {
    Optional<Relation> findRelationshipByUserAndTargetUser(Long userid, Long TargetId);

    List<Relation> findAllByTypeRelationShipLike(Long relationTypeId);

    List<Relation> findAllByUserAndTypeRelationShip_IdLike(Long UserId, Long relationTypeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE relationship\n" +
            "SET relation_type_id = :typeId \n" +
            "WHERE id = :relationId ;", nativeQuery = true)
    int modifyRelationType(@Param("relationId") Long relationId,
                           @Param("typeId") Long typeId);
}
