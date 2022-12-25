package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AppUser {
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String MAN = "MAN";
    public static final String WOMAN = "WOMAN";
    public static final String OTHER = "OTHER";

    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(nullable = false, length = 50)
    private String displayName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate DOB;

//    @OneToMany(mappedBy = "user")
//    private List<Relation> relationUser;
//
//    @OneToMany(mappedBy = "targetUser")
//    private List<Relation> relationsTarget;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    private String status;
    @OneToOne
    @JoinColumn(name = "accountId", unique = true,
            nullable = false, updatable = false)
    private Account account;


    @OneToMany(mappedBy = "user")
    private List<Like> likes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
