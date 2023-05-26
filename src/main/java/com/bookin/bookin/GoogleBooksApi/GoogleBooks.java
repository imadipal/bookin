package com.bookin.bookin.GoogleBooksApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GoogleBooks {

    private int totalItems;
    private List<GoogleApiWrapper> items;


    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleApiWrapper> getItems() {
        return items;
    }

    public void setItems(List<GoogleApiWrapper> items) {
        this.items = items;
    }


}
