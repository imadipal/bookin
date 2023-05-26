package com.bookin.bookin.controller;


import com.bookin.bookin.GoogleBooksApi.GoogleBooks;
import com.bookin.bookin.entity.Book;
import com.bookin.bookin.service.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin("https://adityabook.netlify.app")
@RestController
@RequestMapping("api/admin")
public class GoogleBooksController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GoogleBooksService googleBooksService;


    @RequestMapping ("/googlebooks/{id}")
    public List<Book> getgooglebooks(@PathVariable("id") String id) throws Exception{

        RestTemplate restTemplate=new RestTemplate();
        try {
            GoogleBooks googlebooks = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=" + id + "&maxResults=30", GoogleBooks.class);

            return googleBooksService.getmodifiedbooks(googlebooks);
        }catch(Exception e){
            throw  new HttpClientErrorException(HttpStatus.REQUEST_TIMEOUT);
        }
    }
}
