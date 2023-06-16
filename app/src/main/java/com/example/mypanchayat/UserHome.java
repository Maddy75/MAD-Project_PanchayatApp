package com.example.mypanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mypanchayat.databinding.ActivityUserHomeBinding;

public class UserHome extends AppCompatActivity {

    ActivityUserHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnpmavaas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, AvaasRequest.class);
                startActivity(intent);
            }
        });

        binding.btnnrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this, MGNREGRequest.class);
                startActivity(intent);
            }
        });
    }
}