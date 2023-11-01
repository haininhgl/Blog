package com.example.blogger.dto;

import java.util.Date;

public class CommentDTO {
    private long id;

    private String body;

    private Date createdDate;

    private UserDTO user;

    private PostDTO post;

    public CommentDTO(String body, Date createdDate, UserDTO user, PostDTO post) {
        this.body = body;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
