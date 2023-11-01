package com.example.blogger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.example.blogger.constants.Constants.*;


@Entity
@Table( name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Size(min = MIN_USERNAME_LENGTH, message = "Username must be at least " + MIN_USERNAME_LENGTH + " characters long")
  @NotEmpty(message = "Please enter username")
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @JsonIgnore
  @Size(min = MIN_PASSWORD_LENGTH, message = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters long")
  @NotEmpty(message = "Please enter the password")
  @Column(name = "password", nullable = false)
  private String password;

  @NotBlank
  @Size(max = STRING_MAX_LENGTH)
  @Column(name = "email", unique = true, nullable = false)
  @Email(message = "*Please provide a valid Email")
  private String email;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public User() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public Set<Role> getRoles() {
    return this.roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            "}";
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (!(o instanceof User)) {
//      return false;
//    }
//    return id != null && id.equals(((User) o).id);
//  }
}
