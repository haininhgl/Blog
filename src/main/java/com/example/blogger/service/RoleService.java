package com.example.blogger.service;

import com.example.blogger.entity.Role;
import com.example.blogger.exception.BadRequestException;
import com.example.blogger.exception.ResourceNotFoundException;
import jakarta.annotation.Nullable;

import java.util.Set;

public interface RoleService {

    Role getRoleById(long id) throws ResourceNotFoundException, BadRequestException;

    Set<Role> getRolesByIds(Set<Long> ids);

    @Nullable
    Role getRoleAdmin();
}
