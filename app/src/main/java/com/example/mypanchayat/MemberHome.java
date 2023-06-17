package com.example.mypanchayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mypanchayat.databinding.ActivityMemberHomeBinding;

public class MemberHome extends AppCompatActivity {

    ActivityMemberHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityMemberHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnRation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberHome.this, Ration.class);
                startActivity(intent);
            }
        });
    }
}