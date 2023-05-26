package com.bookin.bookin.controller;

import com.bookin.bookin.kafka.Producer;
import com.bookin.bookin.requestmodels.JwtRequest;
import com.bookin.bookin.service.CustomUserDetailService;
import com.bookin.bookin.util.CustomUserDetails;
import com.bookin.bookin.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


class JwtControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailService customUserDetailService;



    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private Producer producer;
    @InjectMocks
    private JwtController jwtController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testRegister() throws Exception {
        when(customUserDetailService.register((CustomUserDetails) any())).thenReturn(new CustomUserDetails());

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setActive(true);
        customUserDetails.setAuthorities(new ArrayList<>());
        customUserDetails.setName("Name");
        customUserDetails.setPassword("iloveyou");
        customUserDetails.setRole("Role");
        customUserDetails.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(customUserDetails);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(jwtController)
                .build()
                .perform(requestBuilder);
    }


    @Test
    void testGenerateToken() throws Exception {
        when(customUserDetailService.loadUserByUsername((String) any())).thenReturn(new CustomUserDetails());
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setPassword("iloveyou");
        jwtRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\",\"role\":null,\"name\":null}"));
    }


    @Test
    void testWelcome() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/welcome");
        MockMvcBuilders.standaloneSetup(jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("This is private page"));
    }


    @Test
    void testWelcome2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/welcome");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(jwtController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("This is private page"));
    }
}

