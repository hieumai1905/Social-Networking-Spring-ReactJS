package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account1 {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String MAN = "MAN";
    public static final String WOMAN = "WOMAN";
    public static final String OTHER = "OTHER";

    public static final String ACTIVE = "ACTIVE";
    public static final String INACTIVE = "INACTIVE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String password;
    @Transient
    private String confirmPassword;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    private String coverImage;

    private String avatarImage;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(length = 255)
    private String address;

    @Column(name = "display_name", length = 100)
    private String displayName;

    private String gender;

    @Column(unique = true)
    private String phone;

    private String status;

    @Column(name = "about_me", columnDefinition = "TEXT")
    private String aboutMe;

    @OneToMany(mappedBy = "account")
    private List<Like> likes;

    @OneToMany(mappedBy = "userAccount")
    private List<Relation> relationUser;

    @OneToMany(mappedBy = "friendAccount")
    private List<Relation> relationsFriend;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;


    @ManyToMany
    private Set<Role> roles;

    @PrePersist
    public void preCreate() {
        status = ACTIVE;
    }
}
