package com.example.mypanchayat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mypanchayat.databinding.ActivityUserProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.DatabaseReference;


public class UserProfile extends AppCompatActivity {

    ActivityUserProfileBinding binding;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final int REQUEST_IMAGE_CAPTURE = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding.userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_GET_CONTENT);
                intent.setType("image/*");      //Note that -> " image/* " is used to retrieve only the images ... if we want to retrieve of files then we can use -> " */* "
                startActivityForResult(intent, 333);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() !=  null) { // Here getData() function contains the path of the image if user selects any

            Uri sFile = data.getData();
            binding.userProfilePic.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("admin_profile_pictures").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("profilepic").setValue(uri.toString());
                            Toast.makeText(UserProfile.this, "Profile Pic Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
//    private EditText nameEditText;
//    private EditText occupationEditText;
//    private EditText phoneNumberEditText;
//    private EditText emailEditText;
//    private Button saveButton;
//
//// ...
//
//    private void saveDetails() {
//        String name = nameEditText.getText().toString();
//        String occupation = occupationEditText.getText().toString();
//        String phoneNumber = phoneNumberEditText.getText().toString();
//        String email = emailEditText.getText().toString();
//
//        // Create a User object with the details
//        AdminProfile user = new AdminProfile(name, occupation, phoneNumber, email);
//
//        // Generate a new unique key for the user
//        DatabaseReference databaseReference = null;
//        String userId = databaseReference.push().getKey();
//
//        // Save the user details to the database under the generated key
//        databaseReference.child(userId).setValue(user)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(UserProfile.this, "Details saved successfully!", Toast.LENGTH_SHORT).show();
//
//                        // Clear input fields after saving
//                        nameEditText.setText("");
//                        occupationEditText.setText("");
//                        phoneNumberEditText.setText("");
//                        emailEditText.setText("");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(UserProfile.this, "Failed to save details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}




