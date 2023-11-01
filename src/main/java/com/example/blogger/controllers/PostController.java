package com.example.blogger.controllers;

import com.example.blogger.dto.PostDTO;
import com.example.blogger.dto.mapper.PostMapper;
import com.example.blogger.entity.Post;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.request.PostRequest;
import com.example.blogger.response.APIResponse;
import com.example.blogger.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
public class PostController {

    @Value("${maxPageSize}")
    private Integer maxPageSize;

    private final PostService postService;

    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    //Xem tất cả post
//    @GetMapping("/posts")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public APIResponse<List<PostDTO>> getPostList(@Valid PostFilter filter, PaginationRequest paginationRequest) {
//        Pageable pageable = paginationRequest.toPageable(maxPageSize);
//        Page<Post> postPage = postService.getAllPost(filter,pageable);
//        Page<PostDTO> result = postPage.map(this.postMapper::toDto);
//
//        return APIResponse.newSuccessPageResponse(result);
//    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<PostDTO> getPostById(@PathVariable Long id) throws ResourceNotFoundException {
        Post post = postService.getById(id);
        PostDTO postDTO = postMapper.toDto(post);
        return APIResponse.newSuccessResponse(postDTO);
    }

    //Tạo post
    @PostMapping("/posts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<PostDTO> createPost(@Valid @RequestBody PostRequest request) throws ResourceNotFoundException {
        Post post = postService.createPost(request);
        PostDTO postDTO = postMapper.toDto(post);
        return APIResponse.newSuccessResponse(postDTO);
    }

    //Cập nhật post theo id
    @PutMapping("/posts/{id}")
    @PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
    public APIResponse<Post> updatePost(@PathVariable Long id, @RequestBody PostRequest request) throws ForbiddenException, ResourceNotFoundException {
        Post post = postService.updateById(id, request);
        return APIResponse.newSuccessResponse(post);
    }

    //Xóa post theo id
    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<Post> deletePostById(@PathVariable Long id) throws ResourceNotFoundException, ForbiddenException {
        postService.deleteById(id);
        return APIResponse.newSuccessResponse();
    }
}