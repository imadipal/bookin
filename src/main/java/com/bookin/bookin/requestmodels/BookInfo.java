package com.bookin.bookin.requestmodels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {


    private String title;
    private ArrayList<String> authors;
    private String description;

    private int Copies=1;
    


    // creating a constructor class for our BookInfo

}
