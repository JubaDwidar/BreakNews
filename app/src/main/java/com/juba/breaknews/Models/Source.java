package com.juba.breaknews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("Id")
    @Expose
    private String Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
