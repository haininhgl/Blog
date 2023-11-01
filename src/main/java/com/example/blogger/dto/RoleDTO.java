package com.example.blogger.dto;

public class RoleDTO {

    private long id;
    private String name;

    private boolean isSystemRole;

    public RoleDTO() {
    }

    public RoleDTO(long id, String name, boolean isSystemRole) {
        this.id = id;
        this.name = name;
        this.isSystemRole = isSystemRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystemRole() {
        return isSystemRole;
    }

    public void setSystemRole(boolean systemRole) {
        isSystemRole = systemRole;
    }
}
