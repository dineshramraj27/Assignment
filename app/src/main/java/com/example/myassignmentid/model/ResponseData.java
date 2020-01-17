package com.example.myassignmentid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.io.Serializable;

public class ResponseData implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<RowData> rows;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setRows(List<RowData> rows) {
        this.rows = rows;
    }

    public List<RowData> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return
                "ResponseData{" +
                        "title = '" + title + '\'' +
                        ",rows = '" + rows + '\'' +
                        "}";
    }
}