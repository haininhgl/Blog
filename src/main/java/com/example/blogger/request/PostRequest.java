package com.example.blogger.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.blogger.constants.Constants.MAX_TITLE_LENGTH;

public class PostRequest {

    @Size(max = MAX_TITLE_LENGTH, message = "Title must be at least " + MAX_TITLE_LENGTH + " characters long")
    @NotBlank(message = "Please enter the title")
    private String title;

    @NotBlank(message = "Viết gì đi anh zai...")
    private String body;

    private Object fileName;

    public PostRequest(String title, String body, Object fileName) {
        this.title = title;
        this.body = body;
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }
}
