package com.example.gsbmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import com.example.gsbmobile.Adapters.RecyclerViewAdapterDoctors;
import com.example.gsbmobile.Instance.RetrofitClientInstance;
import com.example.gsbmobile.Interface.GsbServices;
import com.example.gsbmobile.Interface.RecyclerViewClickListenerDoctors;
import com.example.gsbmobile.Listeners.RecyclerTouchListenerDoctors;
import com.example.gsbmobile.Models.Doctor;
import com.example.gsbmobile.Models.GetUsers;
import com.example.gsbmobile.Models.User;
import com.example.gsbmobile.databinding.ActivityAccueilBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccueilActivity extends AppCompatActivity {
    private ActivityAccueilBinding binding;
    private String token;
    private String username;
    private ArrayList<Doctor> dataDoctors;
    private User CurrentUser;
    private RecyclerViewAdapterDoctors adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent tIntent = getIntent();
        token = (String)tIntent.getSerializableExtra("token");
        username = (String)tIntent.getSerializableExtra("username");

        GsbServices service =
                RetrofitClientInstance.getRetrofitInstance().create(GsbServices.class);
        Call<GetUsers> call = service.getUser(token);
        call.enqueue(new Callback<GetUsers>() {
            @Override
            public void onResponse(Call<GetUsers> call, Response<GetUsers> response) {
                GetUsers usersList = response.body();
                for(User theUser : usersList.getUsers()){
                    if (theUser.getUsername().equals(username)){
                        CurrentUser = theUser;
                    }
                }
                String text = "Bienvenue "+CurrentUser.getName()+" !";
                binding.tvbienvenue.setText(text);
                dataDoctors = new ArrayList<Doctor>();
                for (String doctor : CurrentUser.getDoctors()){
                    //replace every non numeric with nothing
                    doctor = doctor.replaceAll("[^0-9]", "");
                    //turns String into int
                    int i = Integer.parseInt(doctor);

                    GsbServices service =
                            RetrofitClientInstance.getRetrofitInstance().create(GsbServices.class);
                    Call<Doctor> callDoc = service.doctor(i, token);
                    callDoc.enqueue(new Callback<Doctor>() {
                        @Override
                        public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                            Doctor theDoctor = response.body();
                            dataDoctors.add(theDoctor);
                            adapter = new RecyclerViewAdapterDoctors(dataDoctors);
                            binding.listDoctors.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<Doctor> call, Throwable t) {
                            Toast.makeText(AccueilActivity.this, "error with doctor", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<GetUsers> call, Throwable t) {
                Toast.makeText(AccueilActivity.this, "error with user", Toast.LENGTH_SHORT).show();
            }
        });
        binding.listDoctors.setHasFixedSize(true);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(AccueilActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.listDoctors.setLayoutManager(LayoutManager);
        binding.listDoctors.setFocusable(false);

        binding.listDoctors.addOnItemTouchListener(new RecyclerTouchListenerDoctors(getApplicationContext(), binding.listDoctors, new RecyclerViewClickListenerDoctors() {
            @Override
            public void onClick(View view, int position) {
                Intent dIntent = new Intent(getApplicationContext(), DoctorActivity.class);
                dIntent.putExtra("doctor", dataDoctors.get(position));
                dIntent.putExtra("token", token);
                dIntent.putExtra("user", CurrentUser.getId());
                startActivity(dIntent);
            }
        }));
    }

}