package com.example.gsbmobile.Models;

import com.google.gson.annotations.SerializedName;

public class Visit {
    @SerializedName("id")
    private int id;

    @SerializedName("visiteDate")
    private String date;

    @SerializedName("comment")
    private String comment;

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
