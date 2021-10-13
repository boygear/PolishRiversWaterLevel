package org.boygear.services;

import org.boygear.entities.User;
import org.boygear.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User userAdd(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }
}
