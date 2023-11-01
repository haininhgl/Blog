package com.example.blogger.service;

import com.example.blogger.entity.Post;
import com.example.blogger.entity.Role;
import com.example.blogger.entity.User;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.repository.PostRepository;
import com.example.blogger.request.PostRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements  PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final RoleService roleService;

    public PostServiceImpl(PostRepository accountRepository, UserService userService, RoleService roleService) {
        this.postRepository = accountRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

//    @Override
//    public Page<Post> getAllPost(PostFilter filter, Pageable pageable) {
//        return postRepository.getPostList(filter, pageable);
//    }

    @Override
    public Post createPost(PostRequest request) throws ResourceNotFoundException {
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        Date date = new Date();
        post.setCreatedDate(date);
        post.setLastModifiedDate(date);

        User user = userService.getCurrentLoginUser();

        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post updateById(long id, PostRequest request) throws ForbiddenException, ResourceNotFoundException {
        Post post = getById(id);

        User userLogin = userService.getCurrentLoginUser();

        if (!post.getUser().equals(userLogin)) {
            throw new ForbiddenException("Bạn không có quyền sửa bài viết này.");
        }

        BeanUtils.copyProperties(request, post);
        post.setLastModifiedDate(new Date());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post getById(long id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            throw new ResourceNotFoundException("Post not found!");
        }
        return post;
    }

    @Override
    public void deleteById(long id) throws ResourceNotFoundException, ForbiddenException {
        Post post = getById(id);

        User userLogin = userService.getCurrentLoginUser();
        Role adminRole = roleService.getRoleAdmin();

        if (!post.getUser().equals(userLogin) && !userLogin.getRoles().contains(adminRole)){
            throw new ForbiddenException("Bạn không có quyền xóa bình luận này.");
        }

        postRepository.deleteById(id);
    }
}
