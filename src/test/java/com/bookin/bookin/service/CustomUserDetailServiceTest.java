package com.bookin.bookin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookin.bookin.dao.UserRepository;
import com.bookin.bookin.entity.Users;
import com.bookin.bookin.util.CustomUserDetails;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailServiceTest {
  @Mock private UserRepository userRepository;
  @InjectMocks private CustomUserDetailService customUserDetailService;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegister() {
    Users users = new Users();
    users.setActive(true);
    users.setName("Name");
    users.setPassword("iloveyou");
    users.setRole("Role");
    users.setUsername("janedoe");
    when(userRepository.save((Users) any())).thenReturn(users);
    CustomUserDetails customUserDetails = new CustomUserDetails();
    CustomUserDetails actualRegisterResult = customUserDetailService.register(customUserDetails);
    assertSame(customUserDetails, actualRegisterResult);
    assertTrue(actualRegisterResult.isEnabled());
    assertEquals("Role", actualRegisterResult.getRole());
    assertEquals("iloveyou", actualRegisterResult.getPassword());
    assertEquals("Name", actualRegisterResult.getName());
    verify(userRepository).save((Users) any());
  }

  @Test
  void testRegister2() {
    Users users = new Users();
    users.setActive(true);
    users.setName("Name");
    users.setPassword("iloveyou");
    users.setRole("Role");
    users.setUsername("janedoe");
    when(userRepository.save((Users) any())).thenReturn(users);
    CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
    when(customUserDetails.isActive()).thenReturn(true);
    when(customUserDetails.getName()).thenReturn("Name");
    when(customUserDetails.getPassword()).thenReturn("iloveyou");
    when(customUserDetails.getRole()).thenReturn("Role");
    when(customUserDetails.getUsername()).thenReturn("janedoe");
    doNothing().when(customUserDetails).setActive(anyBoolean());
    doNothing().when(customUserDetails).setName((String) any());
    doNothing().when(customUserDetails).setPassword((String) any());
    doNothing().when(customUserDetails).setRole((String) any());
    customUserDetailService.register(customUserDetails);
    verify(userRepository).save((Users) any());
    verify(customUserDetails).isActive();
    verify(customUserDetails).getName();
    verify(customUserDetails).getPassword();
    verify(customUserDetails).getRole();
    verify(customUserDetails).getUsername();
    verify(customUserDetails).setActive(anyBoolean());
    verify(customUserDetails).setName((String) any());
    verify(customUserDetails).setPassword((String) any());
    verify(customUserDetails).setRole((String) any());
  }

  @Test
  void testLoadUserByUsername() throws UsernameNotFoundException {
    Users users = new Users();
    users.setActive(true);
    users.setName("Name");
    users.setPassword("iloveyou");
    users.setRole("Role");
    users.setUsername("janedoe");
    when(userRepository.findByUsername((String) any())).thenReturn(users);
    UserDetails actualLoadUserByUsernameResult =
        customUserDetailService.loadUserByUsername("janedoe");
    Collection<? extends GrantedAuthority> authorities =
        actualLoadUserByUsernameResult.getAuthorities();
    assertTrue(actualLoadUserByUsernameResult.isEnabled());
    assertEquals("Name", ((CustomUserDetails) actualLoadUserByUsernameResult).getName());
    assertEquals("Role", ((CustomUserDetails) actualLoadUserByUsernameResult).getRole());
    assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
    assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
    verify(userRepository).findByUsername((String) any());
  }
}
