package com.example.blogger.dto.mapper;

import com.example.blogger.dto.RoleDTO;
import com.example.blogger.entity.Role;
import com.example.blogger.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
