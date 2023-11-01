package com.example.blogger.dto.mapper;

import com.example.blogger.dto.CommentDTO;
import com.example.blogger.entity.Comment;
import com.example.blogger.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, PostMapper.class })
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
}
