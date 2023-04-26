package com.example.gsbmobile.Interface;

import com.example.gsbmobile.Models.Doctor;
import com.example.gsbmobile.Models.GetUsers;
import com.example.gsbmobile.Models.Token;
import com.example.gsbmobile.Models.User;
import com.example.gsbmobile.Models.Visit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GsbServices {
    @POST("login_check")
    Call<Token> getToken(@Body User user);

    @GET("users")
    Call<GetUsers> getUser(@Header("Authorization") String token);

    @GET("doctors/{id}")
    Call<Doctor> doctor(@Path("id") int id, @Header("Authorization") String token);

    @GET("visits/{id}")
    Call<Visit> visit(@Path("id") int id, @Header("Authorization") String token);

    @POST("visits")
    Call<Visit> newVisit(@Body Visit visit);
}
