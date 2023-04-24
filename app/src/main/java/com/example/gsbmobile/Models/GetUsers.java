package com.example.gsbmobile.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetUsers {
    @SerializedName("hydra:member")
    ArrayList<User> users;

    public GetUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
