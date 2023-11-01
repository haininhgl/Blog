package com.example.blogger.service;

import com.example.blogger.entity.Role;
import com.example.blogger.entity.RoleType;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(long id) throws ResourceNotFoundException {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null){
            throw new ResourceNotFoundException("Ko tim thay");
        }
        return role;
    }

    @Override
    public Set<Role> getRolesByIds(Set<Long> ids) {
        return roleRepository.findByIdIn(ids);
    }

    @Nullable
    @Override
    public Role getRoleAdmin() {
        return roleRepository.findByName(RoleType.ROLE_ADMIN).orElse(null);
    }
}
