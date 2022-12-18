package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "relations")
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
    private TypeRelationShip typeRelationShip;

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    private Account userAccount;

    @ManyToOne
    @JoinColumn(name = "friend_account_id", nullable = false)
    private Account friendAccount;

    @PrePersist
    public void preCreate() {
        changeAt = new Date();
    }
}
