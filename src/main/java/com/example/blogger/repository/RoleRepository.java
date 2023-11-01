package com.example.blogger.repository;

import com.example.blogger.entity.Role;
import com.example.blogger.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

    Set<Role> findByNameIn(Set<RoleType> names);

    Set<Role> findByIdIn(Set<Long> ids);
}
