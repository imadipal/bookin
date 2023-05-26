package com.bookin.bookin.config;

import com.bookin.bookin.filter.JwtAuthenticationFilter;
import com.bookin.bookin.service.CustomUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.accept.ContentNegotiationStrategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtConfigTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Mock
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Mock
    private ContentNegotiationStrategy contentNegotiationStrategy;

    @Mock
    private CustomUserDetailService customUserDetailService;

    @Mock
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectPostProcessor<Object> objectPostProcessor;

    @InjectMocks
    private JwtConfig jwtConfig;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testPasswordEncoder() {
        assertTrue(jwtConfig.passwordEncoder() instanceof NoOpPasswordEncoder);
    }


}

