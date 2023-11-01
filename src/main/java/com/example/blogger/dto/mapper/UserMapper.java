package com.example.blogger.dto.mapper;

import com.example.blogger.dto.UserDTO;
import com.example.blogger.entity.User;
import com.example.blogger.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper extends EntityMapper<UserDTO, User> {
}

