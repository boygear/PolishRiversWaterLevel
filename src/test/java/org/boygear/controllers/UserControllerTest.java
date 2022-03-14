package org.boygear.controllers;

import org.boygear.DTO.UserDTO;
import org.boygear.entities.User;
import org.boygear.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

@Mock
private UserService userService;

@InjectMocks
private UserController userController;

private UserDTO userDTO = new UserDTO();
private User user = new User();

@Before
public void setUser(){
    user.setUsername("boygear");
    user.setEmail("test@test.com");
    user.setPassword("testPassword");
    userDTO.setUsername("boygear");
    userDTO.setEmail("test@test.com");
    userDTO.setPassword("testPassword");
}

    @Test
    public void users() {
        //given
        List<User> userList = List.of(user);
        Mockito.when(userService.getUsersList()).thenReturn(userList);

        //when
        List<User> resultList = userController.users();

        //then
        assertEquals(resultList, userList);
        Mockito.verify(userService, Mockito.times(1)).getUsersList();
    }

    @Test
    public void usersPost() {
        //given
        Mockito.when(userService.userAdd(userDTO)).thenReturn(user);
        //when
        User returnedUser = userController.usersPost(userDTO);
        //then
        assertEquals(user, returnedUser);
        Mockito.verify(userService, Mockito.times(1)).userAdd(userDTO);
    }

    @Test
    public void userUpdate() {

    }

    @Test
    public void deleteUser() {
    }
}