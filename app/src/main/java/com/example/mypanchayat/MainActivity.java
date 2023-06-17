package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mypanchayat.Models.RationModel;
import com.example.mypanchayat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Signup.class));
            }
        });
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Login.class));

            }
        });
        binding.tvServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Services.class));
            }
        });

        Date currentDate = new Date();
        long currentTimeStamp = currentDate.getTime();
        Log.d("rationDeletionDebug", "Executed.. ");
        database.getReference().child("Ration").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()) {
                    RationModel model = snapshot1.getValue(RationModel.class);
                    model.setRationId(snapshot1.getKey());
                    long rationValidityTime = model.getRationValidityTime();

                    // Convert the timestamps to LocalDate objects
                    Date currentDate = new Date(currentTimeStamp);
                    Date validityDate = new Date(rationValidityTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String currentDateString = dateFormat.format(currentDate);
                    String validityDateString = dateFormat.format(validityDate);
                    Log.d("rationDeletionDebug", "Executed.. Checking condition");
                    if(currentDateString.equals(validityDateString)) {
                        Log.d("rationDeletionDebug", "Condition Satisfied. deleted");
                        database.getReference().child("Ration").child(model.getRationId()).setValue(null);
                    }
                    Log.d("rationDeletionDebug", "Executed.. Condition Failed :- " + currentDateString +validityDateString);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}