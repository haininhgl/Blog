package com.example.blogger.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Table(name = "roles")
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "role", unique = true)
  @Enumerated(EnumType.STRING)
  private RoleType name;

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
  private Collection<User> users;

  private boolean isSystemRole = false;

  public Role() {

  }

  public Role(RoleType name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Collection<User> getUsers() {
    return users;
  }

  public void setUsers(Collection<User> users) {
    this.users = users;
  }

  public RoleType getName() {
    return name;
  }

  public void setName(RoleType name) {
    this.name = name;
  }

  public boolean isSystemRole() {
    return isSystemRole;
  }

  public void setSystemRole(boolean systemRole) {
    isSystemRole = systemRole;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Role role)) return false;
    return Objects.equals(getId(), role.getId()) && getName().name().equals(role.getName().name());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName().name());
  }
}
