package com.example.blogger.service;

import com.example.blogger.entity.Post;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.request.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
//    Page<Post> getAllPost(PostFilter filter, Pageable pageable);

    Post createPost(PostRequest request) throws ResourceNotFoundException;

    Post getById(long id) throws ResourceNotFoundException;

    Post updateById(long id, PostRequest postRequest) throws ForbiddenException, ResourceNotFoundException;

    void deleteById(long id) throws ResourceNotFoundException, ForbiddenException;

}
