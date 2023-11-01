package com.example.blogger.service;

import com.example.blogger.entity.Comment;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.request.CommentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface CommentService {

    Page<Comment> getAllComment(Pageable pageable) throws ResourceNotFoundException;

    Comment createComment(CommentRequest request) throws ResourceNotFoundException;

    void updateById(long id, CommentRequest request) throws ForbiddenException, ResourceNotFoundException;

    void deleteById(long id) throws ResourceNotFoundException, ForbiddenException;
}
