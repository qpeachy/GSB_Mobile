package com.example.gsbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsbmobile.Instance.RetrofitClientInstance;
import com.example.gsbmobile.Interface.GsbServices;
import com.example.gsbmobile.Models.Visit;
import com.example.gsbmobile.databinding.ActivityNewVisitBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewVisitActivity extends AppCompatActivity {
    private ActivityNewVisitBinding binding;
    private String idDoc;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);
        binding = ActivityNewVisitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        idDoc = "/api/doctors/"+ intent.getSerializableExtra("idDoc");
        idUser = "/api/users/"+ intent.getSerializableExtra("idUser");


        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etComment.getText().toString()!=null && binding.etDate.getText().toString()!=null){
                    Visit visit = new Visit(binding.etDate.getText().toString(),binding.etComment.getText().toString(), idUser, idDoc);
                    GsbServices service =
                            RetrofitClientInstance.getRetrofitInstance().create(GsbServices.class);
                    Call<Visit> call = service.newVisit(visit);
                    call.enqueue(new Callback<Visit>() {
                        @Override
                        public void onResponse(Call<Visit> call, Response<Visit> response) {
                            Visit laVisite = response.body();
                            Intent intent = new Intent();
                            intent.putExtra("newVisit", visit);
                            setResult(1, intent);
                            NewVisitActivity.super.onBackPressed(); // ou finish();
                        }

                        @Override
                        public void onFailure(Call<Visit> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}