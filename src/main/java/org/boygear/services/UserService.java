package org.boygear.services;

import org.boygear.DTO.UserDTO;
import org.boygear.entities.Station;
import org.boygear.entities.User;
import org.boygear.exceptions.BadRequestException;
import org.boygear.repositories.StationRepository;
import org.boygear.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private StationRepository stationRepository;

    public User userAdd(UserDTO userDTO) {
        validateUserDTO(userDTO);
        return userRepository.save(mapUserDTOtoUser(userDTO));
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean getUserByName(String name) {
        return userRepository.existsByUsername(name);
    }

    private User mapUserDTOtoUser(UserDTO userDTO){
        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encodedPassword);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }


    public User updateUser(UserDTO changedUserDTO, Integer id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(changedUserDTO.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    validateUserDTO(changedUserDTO);
                    return userRepository.save(mapUserDTOtoUser(changedUserDTO));
                });

    }

    public String deleteUser(Integer id) {

        boolean userExisting = userRepository.existsById(id);
        if (userExisting) {
            userRepository.deleteById(id);
            return "ok";
        } else {
            return "No user in db";
        }


    }

    public List<Station> addFavoriteStationsToUser(List<Long> stationsID, Integer userId) {

        List<Station> stationList = new ArrayList<>();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("user doesn`t exist"));


        for (Long stationID : stationsID) {

            Station station = stationRepository.findById(stationID)
                    .orElseThrow(() -> new BadRequestException("one ore more station doesn`t exist"));

            stationList.add(station);
        }

        Set<Station> usersFavoriteStation = user.getFavoriteStations();
        usersFavoriteStation.addAll(stationList);
        userRepository.save(user);
        return stationList;
    }

    public Set<Station> getUserFavoriteStations(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("user doesn`t exist"));

        return user.getFavoriteStations();

    }

    public boolean checkUserIsAuthorize(Integer ID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserName = authentication.getName();
        User user = getUserById(ID);
        if (user.getUsername().equals(authUserName)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserIsAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean validateUserDTO(UserDTO userDTO) {
        if (userDTO== null ||userDTO.getEmail() == null || userDTO.getPassword() == null || userDTO.getUsername() == null) {
            throw new BadRequestException("Invalid data");
        }else{
            return true;
        }
    }
}
