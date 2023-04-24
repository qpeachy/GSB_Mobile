package com.example.gsbmobile.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("phone")
    private String phone;

    @SerializedName("mail")
    private String mail;

    @SerializedName("street")
    private String street;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("city")
    private String city;

    @SerializedName("coeffNotoriete")
    private int coeffNotoriete;

    @SerializedName("users")
    private ArrayList<String> users;

    @SerializedName("visite")
    private ArrayList<String> visits;

    public String getId() {return id;}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public int getCoeffNotoriete() {
        return coeffNotoriete;
    }

    public String getStreet() {
        return street;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public ArrayList<String> getVisits() {
        return visits;
    }

}
