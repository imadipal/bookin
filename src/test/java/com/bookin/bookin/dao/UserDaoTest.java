package com.bookin.bookin.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bookin.bookin.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserDaoTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserDao userDao;

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddUser() {
    Users users = new Users();
    users.setActive(true);
    users.setName("Name");
    users.setPassword("iloveyou");
    users.setRole("Role");
    users.setUsername("janedoe");
    when(userRepository.save((Users) any())).thenReturn(users);

    Users users1 = new Users();
    users1.setActive(true);
    users1.setName("Name");
    users1.setPassword("iloveyou");
    users1.setRole("Role");
    users1.setUsername("janedoe");
    userDao.addUser(users1);
    verify(userRepository).save((Users) any());
    assertEquals("Name", users1.getName());
    assertTrue(users1.isActive());
    assertEquals("janedoe", users1.getUsername());
    assertEquals("Role", users1.getRole());
    assertEquals("iloveyou", users1.getPassword());
  }

  @Test
  void testFindByUsername() {
    Users users = new Users();
    users.setActive(true);
    users.setName("Name");
    users.setPassword("iloveyou");
    users.setRole("Role");
    users.setUsername("janedoe");
    when(userRepository.findByUsername((String) any())).thenReturn(users);
    assertTrue(userDao.findByUsername("janedoe").isPresent());
    verify(userRepository).findByUsername((String) any());
  }
}
