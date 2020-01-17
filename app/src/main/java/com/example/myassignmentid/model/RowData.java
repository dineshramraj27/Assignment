package com.example.myassignmentid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RowData implements Serializable {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private String imageHref;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    public String getImageHref() {
        return imageHref;
    }

    @Override
    public String toString() {
        return
                "RowData{" +
                        "title = '" + title + '\'' +
                        ",description = '" + description + '\'' +
                        ",imageHref = '" + imageHref + '\'' +
                        "}";
    }
}