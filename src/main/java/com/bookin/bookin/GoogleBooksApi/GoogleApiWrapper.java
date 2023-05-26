package com.bookin.bookin.GoogleBooksApi;

import com.bookin.bookin.requestmodels.BookInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class GoogleApiWrapper {

     private BookInfo volumeInfo;

    public  BookInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(BookInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


}
