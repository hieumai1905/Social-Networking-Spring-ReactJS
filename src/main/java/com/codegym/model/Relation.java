package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "relations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "change_at", nullable = false)
    private Date changeAt;
    @ManyToOne
    @JoinColumn(name = "type_relation_id", nullable = false)
    private TypeRelationship typeRelationShip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "target_user_id", nullable = false)
    private AppUser targetUser;

    @PrePersist
    public void preCreate() {
        changeAt = new Date();
    }
}
