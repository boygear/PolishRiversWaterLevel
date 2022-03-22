package org.boygear.services.userServices;

import org.boygear.entities.User;
import org.boygear.repositories.UserRepository;
import org.boygear.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameWhenNull() {
        //given
        Mockito.when(userRepository.findByUsername("boygear")).thenReturn(null);
        //when
        UserDetails userDetails = userDetailsService.loadUserByUsername("boygear");
    }
    @Test
    public void loadUserByUsername() {
        //given
        User user = new User();

        Mockito.when(userRepository.findByUsername("boygear")).thenReturn(user);
        //when
        UserDetails userDetails = userDetailsService.loadUserByUsername("boygear");
        //then
        assertEquals(user.getUsername(),userDetails.getUsername());
    }
}