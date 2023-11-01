package com.example.blogger.dto.mapper;

import com.example.blogger.dto.PostDTO;
import com.example.blogger.entity.Post;
import com.example.blogger.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface PostMapper extends EntityMapper<PostDTO, Post> {
}
