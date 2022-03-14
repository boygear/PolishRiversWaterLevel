package org.boygear.services;

import org.boygear.DTO.UserDTO;
import org.boygear.entities.User;
import org.boygear.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void userAdd() {
        //given
        UserDTO userDTO = new UserDTO();
        User user = new User();
        user.setUsername("boygear");
        user.setPassword("passowrd");
        user.setEmail("boy@test.pl");
        userDTO.setUsername("boygear");
        userDTO.setPassword("passowrd");
        userDTO.setEmail("boy@test.pl");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        //when
        User userResult = userService.userAdd(userDTO);
        //then
        Assert.assertNotNull(userResult);
        Assert.assertEquals(user.getId(),userResult.getId());
        Assert.assertEquals(user.getEmail(),userResult.getEmail());
        Assert.assertEquals(user.getUsername(),userResult.getUsername());
        Assert.assertEquals(user.getPassword(),userResult.getPassword());

        Mockito.verify(userRepository).save(Mockito.any());
    }

    @Test
    public void getUsersList() {
        //given
        User user = new User();
        user.setUsername("boygear");
        user.setPassword("passowrd");
        user.setEmail("boy@test.pl");

        List<User> userList = Arrays.asList(user);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //when
        List<User> userResult = userService.getUsersList();
        //then
        Mockito.verify(userRepository).findAll();
        assertEquals(userList, userResult);


    }

    @Test
    public void updateUserWhenExist() {
        //given
        User user = new User();
        user.setId(1);
        user.setUsername("boygear");
        user.setPassword("passowrd");
        user.setEmail("boy@test.pl");

        User changedUser = new User();
        changedUser.setId(1);
        changedUser.setUsername("boygear");
        changedUser.setPassword("passowrd");
        changedUser.setEmail("newEmail@test.pl");

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        //when
        userService.updateUser(changedUser, 1);

        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(argumentCaptor.capture());

        assertEquals("newEmail@test.pl", argumentCaptor.getValue().getEmail());
        assertEquals(1, argumentCaptor.getValue().getId());
    }

    @Test
    public void updateUserWhenNew() {
        //given
        User user = new User();
        user.setId(1);
        user.setUsername("boygear");
        user.setPassword("passowrd");
        user.setEmail("boy@test.pl");

        User changedUser = new User();
        changedUser.setId(1);
        changedUser.setUsername("boygear");
        changedUser.setPassword("passowrd");
        changedUser.setEmail("newEmail@test.pl");

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        //when
        userService.updateUser(changedUser, 1);

        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(argumentCaptor.capture());

        assertEquals("newEmail@test.pl", argumentCaptor.getValue().getEmail());
        assertEquals(1, argumentCaptor.getValue().getId());
    }

    @Test
    public void deleteUserFalse() {
        //given
        Integer id = 1;
        Mockito.when(userRepository.existsById(id)).thenReturn(false);
        //when
        String result = userService.deleteUser(id);
        //then
        Mockito.verify(userRepository, Mockito.never()).deleteById(id);
        assertEquals("No user in db",result);

    }
    @Test
    public void deleteUserTrue() {
        //given
        Integer id = 1;
        Mockito.when(userRepository.existsById(id)).thenReturn(true);
        //when
        String result = userService.deleteUser(id);
        //then
        Mockito.verify(userRepository).deleteById(id);
        assertEquals("ok",result);

    }
}