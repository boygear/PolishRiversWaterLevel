package org.boygear.controllers;

import org.boygear.DTO.StationsList;
import org.boygear.DTO.UserDTO;
import org.boygear.entities.Station;
import org.boygear.entities.User;
import org.boygear.exceptions.BadRequestException;
import org.boygear.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public List<User> users() {
            return userService.getUsersList();
    }

    @GetMapping("/users/{id}")
    public User userGetDetail(@PathVariable Integer id) {
        boolean isAuthorizeUser = userService.checkUserIsAuthorize(id);
        if (isAuthorizeUser || userService.checkUserIsAdmin()) {

            return userService.getUserById(id);
        }
        throw new BadRequestException("details not allowed");

    }
    @PostMapping("/users")
    @ResponseBody
    public User usersPost(@RequestBody UserDTO userDTO) {

        if(userDTO == null){
            throw new BadRequestException("user is invalid");
        }
        boolean userExist = userService.getUserByName(userDTO.getUsername());
        if (userExist || !userService.validateUserDTO(userDTO)) {
            throw new BadRequestException("Username already exist or user is invalid");
        }
        return userService.userAdd(userDTO);


    }

    @GetMapping("/users/{id}/favoritestations")
    public Set<Station> getUsersFavoriteStations(@PathVariable(value = "id") Integer userID) {
        boolean isAuthorizeUser = userService.checkUserIsAuthorize(userID);
        if (isAuthorizeUser || userService.checkUserIsAdmin()) {
            return userService.getUserFavoriteStations(userID);
        }
        throw new BadRequestException("details not allowed");
    }

    @PostMapping("/users/{id}/favoritestations")
    public List<Station> addFavoriteUsers(@RequestBody StationsList stationsList, @PathVariable Integer id) {
        boolean isAuthorizeUser = userService.checkUserIsAuthorize(id);
        if (isAuthorizeUser || userService.checkUserIsAdmin()) {
            return userService.addFavoriteStationsToUser(stationsList.getIds(), id);
        }
        throw new BadRequestException("details not allowed");
    }


    @PutMapping("/users/{id}")
    public User userUpdate(@RequestBody UserDTO changedUser, @PathVariable Integer id) {

        boolean isAuthorizeUser = userService.checkUserIsAuthorize(id);
        if (isAuthorizeUser || userService.checkUserIsAdmin()) {
            return userService.updateUser(changedUser, id);
        }
        throw new BadRequestException("details not allowed");
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) {
        boolean isAuthorizeUser = userService.checkUserIsAuthorize(id);
        if (isAuthorizeUser || userService.checkUserIsAdmin()) {
            return userService.deleteUser(id);
        }
        throw new BadRequestException("details not allowed");
    }


}