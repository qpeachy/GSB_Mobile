package com.example.gsbmobile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsbmobile.Adapters.RecyclerViewAdapterVisits;
import com.example.gsbmobile.Instance.RetrofitClientInstance;
import com.example.gsbmobile.Interface.GsbServices;
import com.example.gsbmobile.Interface.RecyclerViewClickListenerVisits;
import com.example.gsbmobile.Listeners.RecyclerTouchListenerVisits;
import com.example.gsbmobile.Models.Doctor;
import com.example.gsbmobile.Models.User;
import com.example.gsbmobile.Models.Visit;
import com.example.gsbmobile.databinding.ActivityDoctorBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {
    private ActivityDoctorBinding binding;
    private RecyclerViewAdapterVisits adapter;
    private ArrayList<Visit> dataVisits;
    private Doctor doctor;
    private int userId;
    private String token;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 1){
                        Intent resultIntent = result.getData();
                        if(resultIntent != null){
                            Visit nVisit = (Visit) resultIntent.getSerializableExtra("newVisit");
                            dataVisits.add(nVisit);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent theIntent = getIntent();
        doctor = (Doctor)theIntent.getSerializableExtra("doctor");
        token = (String)theIntent.getSerializableExtra("token");
        userId = (int)theIntent.getSerializableExtra("iduser");

        dataVisits = new ArrayList<Visit>();

        for(String visit : doctor.getVisits()){
            visit = visit.replaceAll("[^0-9]", "");
            int i = Integer.parseInt(visit);
            GsbServices service =
                    RetrofitClientInstance.getRetrofitInstance().create(GsbServices.class);
            Call<Visit> call = service.visit(i, token);
            call.enqueue(new Callback<Visit>() {
                @Override
                public void onResponse(Call<Visit> call, Response<Visit> response) {
                    Visit visit = response.body();
                    dataVisits.add(visit);
                    adapter = new RecyclerViewAdapterVisits(dataVisits);
                    binding.listVisit.setAdapter(adapter);
                }
                @Override
                public void onFailure(Call<Visit> call, Throwable t) {
                    Toast.makeText(DoctorActivity.this, "error with visit", Toast.LENGTH_SHORT).show();
                }
            });
        }
        binding.listVisit.setHasFixedSize(true);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(DoctorActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.listVisit.setLayoutManager(LayoutManager);
        binding.listVisit.setFocusable(false);

        binding.tvAdress.setText(String.valueOf("Ville: " + doctor.getStreet() + ' ' + doctor.getCity()));
        binding.tvcoeffNot.setText("Coeff notoriété: "+String.valueOf(doctor.getCoeffNotoriete()));
        binding.tvMail.setText("Mail: "+doctor.getMail());
        binding.tvName.setText(doctor.getName());
        binding.tvSurName.setText(doctor.getSurname());
        binding.tvPhoneNum.setText("Numéro de Téléphone: "+doctor.getPhone());

        binding.listVisit.addOnItemTouchListener(new RecyclerTouchListenerVisits(getApplicationContext(), binding.listVisit, new RecyclerViewClickListenerVisits() {
            @Override
            public void onClick(View view, int position) {
                Intent vIntent = new Intent(getApplicationContext(), DetailsVisitActivity.class);
                vIntent.putExtra("visitDetails", dataVisits.get(position));
                startActivity(vIntent);
            }
        }));

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), NewVisitActivity.class);
                intent.putExtra("idDoc", doctor.getId());
                intent.putExtra("idUser", Integer.toString(userId));
                activityResultLauncher.launch(intent);
            }
        });
    }
}