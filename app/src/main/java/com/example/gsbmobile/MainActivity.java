package com.example.gsbmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsbmobile.Instance.RetrofitClientInstance;
import com.example.gsbmobile.Interface.GsbServices;
import com.example.gsbmobile.Models.Token;
import com.example.gsbmobile.Models.User;
import com.example.gsbmobile.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnEntrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("landerson", "Th3G04t");
                GsbServices service =
                        RetrofitClientInstance.getRetrofitInstance().create(GsbServices.class);
                Call<Token> call = service.getToken(user);
                call.enqueue(new Callback<Token>() {

                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        Token token = response.body();
                        Intent intent = new Intent(getApplicationContext(), AccueilActivity.class);
                        intent.putExtra("username", user.getUsername());
                        intent.putExtra("token", token.getToken());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Not allowed to login", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });




    }
}