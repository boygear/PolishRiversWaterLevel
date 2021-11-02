package org.boygear.controllers;

import org.boygear.entities.User;
import org.boygear.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.getUsersList();
    }

    @PostMapping("/users")
    public User usersPost(@RequestBody User user) {

        return userService.userAdd(user);

    }

    @PutMapping("/users/{id}")
    public User userUpdate(@RequestBody User changedUser, @PathVariable Integer id) {
        return userService.updateUser(changedUser, id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}