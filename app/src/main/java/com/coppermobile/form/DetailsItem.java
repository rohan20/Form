package com.coppermobile.form;

public class DetailsItem {

    private String category;
    private String data;

    public DetailsItem(String category, String data) {
        this.category = category;
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
