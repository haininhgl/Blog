package com.example.blogger.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.blogger.constants.Constants.MIN_USERNAME_LENGTH;
import static com.example.blogger.constants.Constants.STRING_MAX_LENGTH;


public class BasicUserRequest {

    @Size(min = MIN_USERNAME_LENGTH)
    @NotBlank(message = "Please enter username")
    private String username;

    @NotBlank(message = "Please enter the email")
    @Size(max = STRING_MAX_LENGTH)
    @Email(message = "Invalid email format")
    private String email;

    public BasicUserRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
