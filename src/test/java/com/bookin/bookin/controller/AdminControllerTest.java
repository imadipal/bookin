package com.bookin.bookin.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import com.bookin.bookin.requestmodels.AddBookRequest;
import com.bookin.bookin.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class AdminControllerTest {
    @Mock
    private AdminService adminService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    private AdminController adminController;
    @Test
    void testIncreaseBookQuantity() throws Exception {
        doNothing().when(adminService).increaseBookQuantity((Long) any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/admin/increase/book/quantity");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("bookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDecreaseBookQuantity() throws Exception {
        doNothing().when(adminService).decreaseBookQuantity((Long) any());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/admin/decrease/book/quantity");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("bookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testPostBook() throws Exception {
        doNothing().when(adminService).postBook((AddBookRequest) any());

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setAuthor("JaneDoe");
        addBookRequest.setCategory("Category");
        addBookRequest.setCopies(1);
        addBookRequest.setDescription("The characteristics of someone or something");
        addBookRequest.setImg("Img");
        addBookRequest.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(addBookRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/admin/add/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(adminService).deleteBook((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/admin/delete/book");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("bookId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

