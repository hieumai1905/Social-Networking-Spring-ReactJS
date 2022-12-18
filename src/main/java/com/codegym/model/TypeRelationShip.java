package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type_relations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeRelationShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_relation_name", nullable = false)
    private String typeRelationshipName;

    @OneToMany(mappedBy = "typeRelationShip")
    List<Relation> relations;
}
