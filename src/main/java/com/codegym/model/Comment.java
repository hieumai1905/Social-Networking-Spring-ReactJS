package com.codegym.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext not null")
    private String content;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "comment_at")
    @Temporal(TemporalType.DATE)
    private Date commentAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @PrePersist
    public void preCreate() {
        commentAt = new Date();
        likeCount = 0;
    }
}
