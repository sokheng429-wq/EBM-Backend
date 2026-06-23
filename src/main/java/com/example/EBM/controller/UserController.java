package com.example.EBM.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EBM.dto.UserDto;
import com.example.EBM.mapper.UserMapper;
import com.example.EBM.model.User;
import com.example.EBM.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(UserMapper.mapToUserDto(savedUser), HttpStatus.CREATED);
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        return new ResponseEntity<>(UserMapper.mapToUserDto(user), HttpStatus.OK);
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @RequestBody UserDto updatedUserDto) {

        User updatedUser = UserMapper.mapToUser(updatedUserDto);
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(UserMapper.mapToUserDto(user));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
