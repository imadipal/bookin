package com.bookin.bookin.controller;

import com.bookin.bookin.requestmodels.AddBookRequest;
import com.bookin.bookin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://adityabook.netlify.app")
@RestController
@RequestMapping("api/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @PutMapping("/increase/book/quantity")
    public void increaseBookQuantity(@RequestParam Long bookId) throws Exception{
        adminService.increaseBookQuantity(bookId);
    }

    @PutMapping("/decrease/book/quantity")
    public void decreaseBookQuantity(@RequestParam Long bookId) throws Exception{
        adminService.decreaseBookQuantity(bookId);
    }

    @PostMapping("/add/book")
    public void postBook(@RequestBody AddBookRequest addBookRequest) throws Exception {
        adminService.postBook(addBookRequest);
    }

    @DeleteMapping("/delete/book")
    public void deleteBook(@RequestParam Long bookId) throws Exception{
        adminService.deleteBook(bookId);

    }

}
