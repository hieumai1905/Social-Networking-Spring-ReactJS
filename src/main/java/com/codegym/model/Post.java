package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext")
    private String content;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser user;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "post")
    private List<Image> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @PrePersist
    public void preCreate() {
        likeCount = 0;
        commentCount = 0;
        createAt = new Date();
    }
}
