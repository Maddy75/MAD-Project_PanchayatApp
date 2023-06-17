package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mypanchayat.Adapters.RationAdapter;
import com.example.mypanchayat.Models.RationModel;
import com.example.mypanchayat.databinding.ActivityRationDisplayBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RationDisplay extends AppCompatActivity {

    ActivityRationDisplayBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ArrayList<RationModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityRationDisplayBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        String selectedState = getIntent().getStringExtra("state");
        String selectedDistrict = getIntent().getStringExtra("district");
        String selectedTaluka = getIntent().getStringExtra("taluka");
        String selectedVillage = getIntent().getStringExtra("village");
        String selectedward = getIntent().getStringExtra("ward");

        RationAdapter adapter = new RationAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(manager);


        database.getReference().child("Ration").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    RationModel model = snapshot1.getValue(RationModel.class);

                    if(selectedState.equals(model.getStateName()) &&
                        selectedDistrict.equals(model.getDistrictName()) &&
                        selectedTaluka.equals(model.getTalukaName()) &&
                        selectedVillage.equals(model.getVillageName()) &&
                        selectedward.equals(model.getWardName())) {
                        list.add(model);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}