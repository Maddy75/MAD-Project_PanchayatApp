package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.mypanchayat.Adapters.ComplaintsAdapter;
import com.example.mypanchayat.Models.ComplaintsModel;
import com.example.mypanchayat.databinding.ActivityComplaintsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Complaints extends AppCompatActivity {

    ActivityComplaintsBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ArrayList<ComplaintsModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityComplaintsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ComplaintsAdapter adapter = new ComplaintsAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager);

        database.getReference().child("Complaints").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ComplaintsModel model = snapshot1.getValue(ComplaintsModel.class);
                    model.setComplaintId(snapshot1.getKey());
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}