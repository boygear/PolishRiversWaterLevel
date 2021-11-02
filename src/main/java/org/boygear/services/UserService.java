package org.boygear.services;

import org.boygear.entities.User;
import org.boygear.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public User updateUser(User changedUser, Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(changedUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    return userRepository.save(changedUser);
                });

    }

    public String deleteUser(Integer id) {

        boolean userExisting = userRepository.existsById(id);
        if(userExisting) {
            userRepository.deleteById(id);
            return "ok";
        }else {
            return "No user in db";
        }



    }
}
