package com.example.gsbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gsbmobile.Models.Visit;
import com.example.gsbmobile.databinding.ActivityAccueilBinding;
import com.example.gsbmobile.databinding.ActivityVisiteBinding;

public class VisitActivity extends AppCompatActivity {
    private ActivityVisiteBinding binding;
    private Visit theVisit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite);
        binding = ActivityVisiteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent vIntent = getIntent();
        theVisit = (Visit)vIntent.getSerializableExtra("visit");

        binding.tvDate.setText(theVisit.getDate());
        binding.tvInfo.setText(theVisit.getComment());
    }
}