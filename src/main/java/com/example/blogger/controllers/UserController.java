package com.example.blogger.controllers;

import com.example.blogger.dto.UserDTO;
import com.example.blogger.dto.mapper.UserMapper;
import com.example.blogger.entity.Role;
import com.example.blogger.entity.User;
import com.example.blogger.exception.BadRequestException;
import com.example.blogger.exception.ForbiddenException;
import com.example.blogger.exception.ResourceNotFoundException;
import com.example.blogger.request.BasicUserRequest;
import com.example.blogger.request.UserRequest;
import com.example.blogger.response.APIResponse;
import com.example.blogger.service.RoleService;
import com.example.blogger.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Value("${maxPageSize}")
    private Integer maxPageSize;

    private final UserService userService;

    private final UserMapper userMapper;

    private final RoleService roleService;

    public UserController(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

//    @GetMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
//    public APIResponse<List<UserDTO>> getUserList(@Valid UserFilter userFilter, PaginationRequest paginationRequest)
//            throws ResourceNotFoundException {
//
//        Pageable pageable = paginationRequest.toPageable(maxPageSize);
//
//        Page<User> userPage = userService.getAllUser(userFilter, pageable);
//        Page<UserDTO> result = userPage.map(this.userMapper::toDto);
//        return APIResponse.newSuccessPageResponse(result);
//    }

    //tao user
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public APIResponse<User> createUser(@Valid @RequestBody UserRequest request) throws BadRequestException {
        User user = validateCreateUser(request);
        userService.createUser(user);
        return APIResponse.newSuccessResponse();
    }

    @GetMapping("/users/info")
    public APIResponse<UserDTO> info() throws ResourceNotFoundException {
        User user = userService.getCurrentLoginUser();

        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    //cap nhat user
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<UserDTO> updateUser(@PathVariable long id, @Valid @RequestBody BasicUserRequest request)
            throws ResourceNotFoundException, ForbiddenException {
        User user = userService.getUserById(id);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());

        user = userService.updateUser(user);

        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    @GetMapping("users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public APIResponse<UserDTO> getUserById(@PathVariable long id) throws ResourceNotFoundException {
        User user = userService.getUserById(id);
        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    //xoa user
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public APIResponse<String> deleteAccountById(@PathVariable long id) throws ResourceNotFoundException {
        userService.deleteUser(id);
        return APIResponse.newSuccessResponse();
    }

    private User validateCreateUser(UserRequest request)
            throws BadRequestException {
        User user = new User();

        Set<Role> roleList = roleService.getRolesByIds(request.getRoleIds());
        if (CollectionUtils.isEmpty(roleList)) {
            throw new BadRequestException("Nhóm quyền không được bỏ trống.");
        }

        user.setRoles(roleList);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return user;
    }
}

