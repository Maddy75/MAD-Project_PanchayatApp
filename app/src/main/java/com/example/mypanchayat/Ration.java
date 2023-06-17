package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mypanchayat.Models.RationModel;
import com.example.mypanchayat.databinding.ActivityRationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ration extends AppCompatActivity {

    ActivityRationBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String selectedStateItem, selectedDistrictItem, selectedTalukItem, selectedVillageItem, selectedWardItem, selectedItemName, rationPic;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityRationBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        // STATE spinner values
        String[] stateValues = {"   ---   ", "Karnataka"};
        ArrayAdapter<String> adapterState = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateValues);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerState.setAdapter(adapterState);

        // DISTRICT Spinner
        List<String> districtValues = new ArrayList<>();
        ArrayAdapter<String> adapterDistrict = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, districtValues);
        adapterDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerDistrict.setAdapter(adapterDistrict);

        // TALUKA Spinner
        List<String> talukaValues = new ArrayList<>();
        ArrayAdapter<String> adapterTaluka = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, talukaValues);
        adapterTaluka.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTaluka.setAdapter(adapterTaluka);

        // Village Spinner
        List<String> villageValues = new ArrayList<>();
        ArrayAdapter<String> adapterVillage = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villageValues);
        adapterVillage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerVillage.setAdapter(adapterVillage);

        // Village Spinner
        List<String> wardValues = new ArrayList<>();
        ArrayAdapter<String> adapterWard = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, wardValues);
        adapterWard.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerWard.setAdapter(adapterWard);

        String[] rationList = {"      ", "Rice", "Oil", "Wheat", "Sugar", "Salt", "Dal", "Kerosene"};
        ArrayAdapter<String> adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rationList);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerItem.setAdapter(adapterItems);

        binding.spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                selectedStateItem = (String) binding.spinnerState.getItemAtPosition(position);
                districtValues.clear();
                if(selectedStateItem.equals("Karnataka")) {
                    districtValues.add("   ---   ");
                    districtValues.add("Bengaluru Urban");
                    districtValues.add("Bengaluru Rural");
                    districtValues.add("Belagavi");
                    districtValues.add("Chikkamagaluru");
                    districtValues.add("Dakshina Kannada");
                }
                adapterDistrict.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Ration.this, "Select values from the list to continue", Toast.LENGTH_SHORT).show();
            }
        });


        binding.spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDistrictItem = (String) binding.spinnerDistrict.getItemAtPosition(i);
                talukaValues.clear();
                talukaValues.add("   ---   ");
                if(selectedDistrictItem.equals("Bengaluru Urban")) {
                    talukaValues.add("Doddaballapur");
                    talukaValues.add("Devanahalli");
                    talukaValues.add("Hosakote");
                    talukaValues.add("Nelamangala");
                } else if (selectedDistrictItem.equals("Bengaluru Rural")) {
                    talukaValues.add("Doddaballapur");
                    talukaValues.add("Devanahalli");
                    talukaValues.add("Hosakote");
                    talukaValues.add("Nelamangala");
                } else if (selectedDistrictItem.equals("Belagavi")) {
                    talukaValues.add("Athani");
                    talukaValues.add("Bailhongal");
                    talukaValues.add("Belgaum");
                    talukaValues.add("Chikkodi");
                    talukaValues.add("Gokak");
                    talukaValues.add("Hukkeri");
                    talukaValues.add("Saundatti");
                    talukaValues.add("Raybag");
                    talukaValues.add("Ramdurg");
                    talukaValues.add("Khanapur");
                } else if (selectedDistrictItem.equals("Chikkamagaluru")) {
                    talukaValues.add("Kadur");
                    talukaValues.add("Mudigere");
                    talukaValues.add("Chikmagalur");
                    talukaValues.add("Koppa");
                    talukaValues.add("Tarikere");
                    talukaValues.add("Sringeri");
                    talukaValues.add("N. R. Pura");
                } else if (selectedDistrictItem.equals("Dakshina Kannada")) {
                    talukaValues.add("Mangalore");
                    talukaValues.add("Bantwal");
                    talukaValues.add("Puttur");
                    talukaValues.add("Belthangady");
                    talukaValues.add("Sullia");
                }
                adapterTaluka.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Ration.this, "Select District to continue", Toast.LENGTH_SHORT).show();
            }
        });

        binding.spinnerTaluka.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTalukItem = (String) binding.spinnerTaluka.getItemAtPosition(i);
                villageValues.clear();
                villageValues.add("   ---   ");
                if(selectedTalukItem.equals("Mangalore")) {
                    villageValues.add("Adyar");
                    villageValues.add("Badagaulipady");
                    villageValues.add("Elathur");
                    villageValues.add("Hosabettu");
                    villageValues.add("Kadandale");
                    villageValues.add("Kallamundkooru");
                    villageValues.add("Kilenjur");
                    villageValues.add("Kondemula");
                    villageValues.add("Muchur");
                    villageValues.add("Padukonaje");
                }
                adapterVillage.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Ration.this, "Select a Taluk to Continue", Toast.LENGTH_SHORT).show();
            }
        });

        binding.spinnerVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedVillageItem = (String) binding.spinnerVillage.getItemAtPosition(i);
                wardValues.clear();
                wardValues.add("   ---   ");
                if(selectedVillageItem.equals("Adyar")) {
                    wardValues.add("WARD NO-18");
                    wardValues.add("WARD NO-21");
                    wardValues.add("WARD NO-48");
                    wardValues.add("WARD NO-52");
                    wardValues.add("WARD NO-65");
                }
                adapterWard.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Ration.this, "Select a ward to continue", Toast.LENGTH_SHORT).show();
            }
        });


        binding.spinnerWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedWardItem = (String) binding.spinnerWard.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemName = (String) binding.spinnerItem.getItemAtPosition(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {

            Date currentDate = new Date();
            long currentTimeStamp = currentDate.getTime();
            Instant currentTimeStmp = Instant.now();
            Instant futureTimeStamp = currentTimeStmp.plus(15, ChronoUnit.DAYS);
            long futureTimeMillis = futureTimeStamp.toEpochMilli();

            @Override
            public void onClick(View view) {

//                StorageReference reference = storage.getReference().child("Ration/" + selectedItemName + ".jpg");
//                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        rationPic = uri.toString();
//                    }
//                });

                RationModel newRation = new RationModel(
                        auth.getUid(),
                        selectedStateItem,
                        selectedDistrictItem,
                        selectedTalukItem,
                        selectedVillageItem,
                        selectedWardItem,
                        selectedItemName,
                        binding.etQuantity.getText().toString(),
                        currentTimeStamp,
                        futureTimeMillis,
                        rationPic);

                database.getReference().child("Ration").push().setValue(newRation).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Ration.this, "Request Submitted!!", Toast.LENGTH_LONG).show();
                            binding.spinnerState.setSelection(0);
                            districtValues.clear();
                            adapterDistrict.notifyDataSetChanged();
                            talukaValues.clear();
                            adapterTaluka.notifyDataSetChanged();
                            villageValues.clear();
                            adapterVillage.notifyDataSetChanged();
                            wardValues.clear();
                            adapterWard.notifyDataSetChanged();
                            binding.spinnerItem.setSelection(0);
                            binding.etQuantity.setText("");
                        }
                    }
                });

            }
        });
    }
}