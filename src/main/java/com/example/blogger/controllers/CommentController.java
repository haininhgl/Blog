package com.example.blogger.controllers;

import com.example.blogger.dto.CommentDTO;
import com.example.blogger.dto.mapper.CommentMapper;
import com.example.blogger.entity.Comment;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.request.CommentRequest;
import com.example.blogger.request.PaginationRequest;
import com.example.blogger.response.APIResponse;
import com.example.blogger.service.CommentService;
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
public class CommentController {

    @Value("${maxPageSize}")
    private Integer maxPageSize;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/comments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public APIResponse<List<CommentDTO>> getCommentList(@RequestParam(required = true) String postId, PaginationRequest paginationRequest)
    public APIResponse<List<CommentDTO>> getCommentList(PaginationRequest paginationRequest)
            throws ResourceNotFoundException {

        Pageable pageable = paginationRequest.toPageable(maxPageSize);

        Page<Comment> commentPage = commentService.getAllComment(pageable);
        Page<CommentDTO> result = commentPage.map(this.commentMapper::toDto);
        return APIResponse.newSuccessPageResponse(result);
    }

    //viet comment
    @PostMapping("/comments")
    @PreAuthorize("hasRole('USER')")
    public APIResponse<CommentDTO> createComment(@RequestBody @Valid CommentRequest request) throws ResourceNotFoundException {
        Comment newComment = commentService.createComment(request);
        CommentDTO commentDTO = commentMapper.toDto(newComment);
        return APIResponse.newSuccessResponse(commentDTO);
    }

    //cap nhat comment
    @PutMapping("/comments/{id}")
    @PreAuthorize("hasRole('USER')")
    public APIResponse<String> updateById(@PathVariable Long id, @RequestBody CommentRequest request) throws ForbiddenException, ResourceNotFoundException {
        commentService.updateById(id, request);

        return APIResponse.newSuccessResponse();
    }

    //Xoa comment theo id
    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<String> deleteById(@PathVariable Long id) throws ResourceNotFoundException, ForbiddenException {
        commentService.deleteById(id);

        return APIResponse.newSuccessResponse();
    }
}

