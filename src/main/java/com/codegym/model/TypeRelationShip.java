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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeRelationshipName() {
        return typeRelationshipName;
    }

    public void setTypeRelationshipName(String typeRelationshipName) {
        this.typeRelationshipName = typeRelationshipName;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }
}
