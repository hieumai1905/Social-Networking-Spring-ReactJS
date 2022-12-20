package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.*;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Entity
@Table(name = "accounts")
@Transactional
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"roles", "authorities", "hibernateLazyInitializer", "handler"})

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(length = 50, unique = true)
    private String email;
    @Column(unique=true, length = 50)
    private String username;
    private String password;

    @ManyToMany
    private Set<Role> roles;
}