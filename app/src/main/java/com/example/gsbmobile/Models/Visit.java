package com.example.gsbmobile.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visit implements Serializable {

    @SerializedName("visiteDate")
    private String date;

    @SerializedName("comment")
    private String comment;

    @SerializedName("doctor")
    private String idDoctor;

    @SerializedName("user")
    private String idUser;

    public Visit(String date, String comment, String idUser, String idDoctor) {
        this.date = date;
        this.comment = comment;
        this.idDoctor = idDoctor;
        this.idUser = idUser;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public String getIdUser() {
        return idUser;
    }
}
