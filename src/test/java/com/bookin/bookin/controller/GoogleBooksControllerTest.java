package com.bookin.bookin.controller;

import com.bookin.bookin.service.GoogleBooksService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {GoogleBooksController.class})
@ExtendWith(SpringExtension.class)
class GoogleBooksControllerTest {
    @Autowired
    private GoogleBooksController googleBooksController;

    @MockBean
    private GoogleBooksService googleBooksService;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link GoogleBooksController#getgooglebooks(String)}
     */
    @Test
    void testGetgooglebooks() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(googleBooksController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

