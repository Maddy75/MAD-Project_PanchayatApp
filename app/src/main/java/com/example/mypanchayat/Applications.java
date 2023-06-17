package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mypanchayat.Adapters.ApplicationAdapter;
import com.example.mypanchayat.Models.Requests;
import com.example.mypanchayat.databinding.ActivityApplicationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Applications extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ActivityApplicationsBinding binding;
    ArrayList<Requests> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityApplicationsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ApplicationAdapter applicationAdapter = new ApplicationAdapter(list, this);
        binding.recyclerView.setAdapter(applicationAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Requests request = snapshot1.getValue(Requests.class);
                    request.setUserId(snapshot1.getKey());
                    list.add(request);
                }
                applicationAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Applications.this, "Couldn't Load Applications!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}