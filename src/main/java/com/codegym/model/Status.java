package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
