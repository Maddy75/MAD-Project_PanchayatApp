package com.example.mypanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mypanchayat.databinding.ActivityAvaasRequestBinding;

public class AvaasRequest extends AppCompatActivity {

    ActivityAvaasRequestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityAvaasRequestBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        
    }
}