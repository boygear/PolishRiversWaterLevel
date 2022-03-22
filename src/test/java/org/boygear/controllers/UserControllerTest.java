package org.boygear.controllers;

import org.boygear.DTO.StationsList;
import org.boygear.DTO.UserDTO;
import org.boygear.entities.Station;
import org.boygear.entities.User;
import org.boygear.exceptions.BadRequestException;
import org.boygear.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO = new UserDTO();
    private final User user = new User();
    private Station station = new Station();

    @Before
    public void setUser() {
        user.setUsername("boygear");
        user.setEmail("test@test.com");
        user.setPassword("testPassword");
        userDTO.setUsername("boygear");
        userDTO.setEmail("test@test.com");
        userDTO.setPassword("testPassword");
        station.setProvince("xxx");
        station.setStationID(24L);
        station.setRiver("Odra");
        station.setName("xxx");
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
    public void userDetails() {
        //given
        Integer id = 1;
        User user = new User();
        Mockito.when(userService.getUserById(id)).thenReturn(user);
        Mockito.when(userService.checkUserIsAdmin()).thenReturn(true);

        //when
        User userResult = userController.userGetDetail(id);

        //then
        assertEquals(userResult, user);
    }

    @Test
    public void userDetailsWithNoUser() {
        //given
        Integer id = 1;
        Mockito.when(userService.getUserById(id)).thenReturn(null);
        Mockito.when(userService.checkUserIsAdmin()).thenReturn(true);

        //when
        User userResult = userController.userGetDetail(id);

        //then
        assertNull(userResult);
    }

    @Test
    public void getUsersFavoriteStations() {
        //Given
        Integer id = 1;
        Station station = new Station();
        Set<Station> stations = new HashSet<>();
        stations.add(station);
        Mockito.when(userService.getUserFavoriteStations(id)).thenReturn(stations);
        Mockito.when(userService.checkUserIsAdmin()).thenReturn(true);
        //When
        Set<Station> stationsResult = userController.getUsersFavoriteStations(1);
        //Then
        assertEquals(stations, stationsResult);
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

    @Test(expected = BadRequestException.class)
    public void userPostWithNull() {
        //given
        Mockito.when(userService.getUserByName("boygear")).thenReturn(false);
        Mockito.when(userService.validateUserDTO(userDTO)).thenThrow(new BadRequestException("Invalid data"));
        //when
        User returnedUser = userController.usersPost(null);

    }

    @Test(expected = BadRequestException.class)
    public void userPostWithInvalidData() {
        //given
        userDTO.setUsername(null);
        Mockito.when(userService.getUserByName(userDTO.getUsername())).thenReturn(false);
        Mockito.when(userService.validateUserDTO(userDTO)).thenReturn(false);
        Mockito.when(userService.userAdd(userDTO)).thenReturn(user);
        //when
        User returnedUser = userController.usersPost(userDTO);
    }

    @Test(expected = BadRequestException.class)
    public void getUsersFavoriteStationsWithNullID() {
        //Given
        Integer id = null;
        Station station = new Station();
        Set<Station> stations = new HashSet<>();
        stations.add(station);
        Mockito.when(userService.getUserFavoriteStations(null)).thenThrow(new BadRequestException("user doesn`t exist"));
        Mockito.when(userService.checkUserIsAdmin()).thenReturn(true);
        //When
        Set<Station> stationsResult = userController.getUsersFavoriteStations(null);
    }

    @Test
    public void addFavoriteUsers() {
        //given
        Integer id = 1;
        List<Station> stations = new ArrayList<>();
        stations.add(station);
        List<Long> idsList = List.of(24L);

        StationsList stationsList = new StationsList();
        stationsList.setIds(idsList);

        Mockito.when((userService.checkUserIsAdmin())).thenReturn(true);
        Mockito.when(userService.addFavoriteStationsToUser(idsList,id)).thenReturn(stations);
        //when
        List<Station> stationResultList = userController.addFavoriteUsers(stationsList, id);
        //then
        assertEquals(stationResultList, stations);
        Mockito.verify(userService).addFavoriteStationsToUser(idsList, id);
    }


    @Test
    public void userUpdate() {
        //given
        Integer id = 1;
        Mockito.when((userService.checkUserIsAdmin())).thenReturn(true);
        Mockito.when((userService.updateUser(userDTO, id))).thenReturn(user);
        //when
        User userResult = userController.userUpdate(userDTO, id);
        //then
        assertEquals(userResult,user);
    }

    @Test
    public void deleteUser() {
        //given
        Mockito.when((userService.checkUserIsAdmin())).thenReturn(true);
        Integer id = 1;
        Mockito.when(userService.deleteUser(id)).thenReturn("ok");
        //when
        String result = userController.deleteUser(id);
        //then
        assertEquals("ok", result);
    }
}