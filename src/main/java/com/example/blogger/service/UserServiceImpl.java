package com.example.blogger.service;

import com.example.blogger.SecurityUtils;
import com.example.blogger.entity.Role;
import com.example.blogger.entity.User;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

//    @Override
//    public Page<User> getAllUser(UserFilter userFilter, Pageable pageable) {
//        return userRepository.getUserList(userFilter, pageable);
//    }

    @Override
    public User getUserById(long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Tao user
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getCurrentLoginUser() throws ResourceNotFoundException {
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        if (login == null) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng đang đăng nhập.");
        }

        return getUserByLogin(login);
    }


    public User getUserByLogin(String login) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(login).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng.");
        }

        return user;
    }

    // Cap nhat user
    @Override
    public User updateUser(User user) throws ResourceNotFoundException, ForbiddenException {
        User userLogin = getCurrentLoginUser();

        long adminRoleId = roleService.getRoleAdmin().getId();
        Set<Long> userRoleIds = userLogin.getRoles().stream().map(Role::getId).collect(Collectors.toSet());

        if (!user.equals(userLogin) && !userRoleIds.contains(adminRoleId)) {
            throw new ForbiddenException("Thao tác không hợp lệ");
        }

        return userRepository.save(user);
    }


    // Xoa user
    @Override
    public void deleteUser(long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found!");
        }

        userRepository.deleteById(id);
    }
}
