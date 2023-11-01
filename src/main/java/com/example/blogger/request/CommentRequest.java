package com.example.blogger.request;

import javax.validation.constraints.NotBlank;

public class CommentRequest {

    @NotBlank
            (message = "Bình luận gì đi?")
    private String body;

    @NotBlank(message = "Bài viết nào?")
    private long postId;

    public CommentRequest() {
    }

    public CommentRequest(String body, Long postId) {
        this.body = body;
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
