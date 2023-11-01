package com.example.blogger.service;

import com.example.blogger.entity.User;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;

public interface UserService {

//    Page<User> getAllUser(UserFilter userFilter, Pageable pageable) throws ResourceNotFoundException;

    User getUserById(long id) throws ResourceNotFoundException;

    User createUser(User user);

    User getUserByLogin(String login) throws ResourceNotFoundException;

    User updateUser(User user) throws ResourceNotFoundException, ForbiddenException;

    User getCurrentLoginUser() throws ResourceNotFoundException;

    void deleteUser(long id) throws ResourceNotFoundException;
}
