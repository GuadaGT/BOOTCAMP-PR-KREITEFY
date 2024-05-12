package com.kreitek.kreitefy.kreitefy.infraestructure.rest;
import com.kreitek.kreitefy.kreitefy.application.dto.UserDto;
import com.kreitek.kreitefy.kreitefy.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/userByUsername/{username}", produces = "application/json")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/user/{userId}", produces = "application/json")
    ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/user/{userId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        Optional<UserDto> existingUser = userService.updateUser(userId, userDto);
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(existingUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
