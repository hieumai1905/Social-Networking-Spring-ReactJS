package com.codegym.repository;

import com.codegym.model.TypeRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRelationTypeRepo extends JpaRepository<TypeRelationship, Long> {
}
