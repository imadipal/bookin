package com.bookin.bookin.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class JwtUtilTest {
    @InjectMocks
    private JwtUtil jwtUtil;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGenerateToken() {
        CustomUserDetails customUserDetails = mock(CustomUserDetails.class);
        ReflectionTestUtils.setField(jwtUtil, "SECRET_KEY", "tmnebatxzn");

        when(customUserDetails.getUsername()).thenReturn("janedoe");
        jwtUtil.generateToken(customUserDetails);
        verify(customUserDetails).getUsername();
    }





}
