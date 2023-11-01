package com.example.blogger.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.example.blogger.constants.Constants.STRING_MAX_LENGTH;

public class UserRequest {

    @Size(max = STRING_MAX_LENGTH)
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    protected  Set<Long> roleIds = new HashSet<>();

    public UserRequest() {
    }

    public UserRequest(String username, String password, String email, Set<Long> roleIds) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleIds = roleIds;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
