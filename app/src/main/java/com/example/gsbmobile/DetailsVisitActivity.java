package com.example.gsbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gsbmobile.Models.Visit;
import com.example.gsbmobile.databinding.ActivityDetailsVisitBinding;

public class DetailsVisitActivity extends AppCompatActivity {
    private ActivityDetailsVisitBinding binding;
    private Visit theVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_visit);
        binding = ActivityDetailsVisitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent vIntent = getIntent();
        theVisit = (Visit)vIntent.getSerializableExtra("visitDetails");

        binding.tvDate.setText(theVisit.getDate());
        binding.tvInfo.setText(theVisit.getComment());
    }
}