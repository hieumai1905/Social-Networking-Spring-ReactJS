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
    @JoinColumn(name = "app_user_current_id",  nullable = false)
    private AppUser userCurrent;

    @ManyToOne
    @JoinColumn(name = "app_user_friend_id", nullable = false)
    private AppUser userFriend;

    @PrePersist
    public void preCreate() {
        changeAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(Date changeAt) {
        this.changeAt = changeAt;
    }

    public TypeRelationShip getTypeRelationShip() {
        return typeRelationShip;
    }

    public void setTypeRelationShip(TypeRelationShip typeRelationShip) {
        this.typeRelationShip = typeRelationShip;
    }

    public AppUser getUserCurrent() {
        return userCurrent;
    }

    public void setUserCurrent(AppUser userCurrent) {
        this.userCurrent = userCurrent;
    }

    public AppUser getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(AppUser userFriend) {
        this.userFriend = userFriend;
    }
}
