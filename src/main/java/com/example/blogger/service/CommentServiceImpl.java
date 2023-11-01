package com.example.blogger.service;

import com.example.blogger.entity.Comment;
import com.example.blogger.entity.Post;
import com.example.blogger.entity.Role;
import com.example.blogger.entity.User;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.repository.CommentRepository;
import com.example.blogger.repository.PostRepository;
import com.example.blogger.request.CommentRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final UserService userService;

    private final RoleService roleService;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, RoleService roleService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.postRepository = postRepository;
    }
    @Override
    public Page<Comment> getAllComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment createComment(CommentRequest request) throws ResourceNotFoundException {
        User user = userService.getCurrentLoginUser();
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);

        comment.setUser(user);
        comment.setCreatedDate(new Date());

        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public void updateById(long id, CommentRequest request) throws ForbiddenException, ResourceNotFoundException {
        Comment comment = getById(id);

        User userLogin = userService.getCurrentLoginUser();

        if (!comment.getUser().equals(userLogin)) {
            throw new ForbiddenException("Bạn không có quyền sửa bình luận này.");
        }

        BeanUtils.copyProperties(request, comment);
        comment.setLastModifiedDate(new Date());
        commentRepository.save(comment);
    }

    private Comment getById(long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null){
            throw new ResourceNotFoundException("Comment not found!");
        }
        return comment;
    }

    @Override
    public void deleteById(long id) throws ResourceNotFoundException, ForbiddenException {
        Comment comment = getById(id);

        User userLogin = userService.getCurrentLoginUser();
        Long adminRoleId = roleService.getRoleAdmin().getId();
        Set<Long> userRoleIds = userLogin.getRoles().stream().map(Role::getId).collect(Collectors.toSet());

        if (!comment.getUser().equals(userLogin) && !userRoleIds.contains(adminRoleId)) {
            throw new ForbiddenException("Bạn không có quyền xóa bình luận này.");
        }

        commentRepository.deleteById(id);
    }
}
