package com.example.mypanchayat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypanchayat.Models.ComplaintsModel;
import com.example.mypanchayat.Models.Users;
import com.example.mypanchayat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ViewHolder> {

    ArrayList<ComplaintsModel> list;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Context context;

    public  ComplaintsAdapter(ArrayList<ComplaintsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ComplaintsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_complaints_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintsAdapter.ViewHolder holder, int position) {
        ComplaintsModel model = list.get(position);

        database.getReference().child("Users").child(model.getUserId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);

                holder.tvName.setText(user.getName());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        holder.tvAbout.setText(model.getAbout());
        holder.tvDescription.setText(model.getDescription());
        holder.tvArea.setText(model.getVillageName() + ", " + model.getWardName().subSequence(0,4) + model.getWardName().subSequence(8,10));
        holder.tvComplaintNo.setText("Complaint - " + (position+1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvComplaintNo, tvName, tvArea, tvAbout, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvComplaintNo = itemView.findViewById(R.id.tvComplaintNumber);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAbout = itemView.findViewById(R.id.tvAbout);
            tvName = itemView.findViewById(R.id.tvName);

        }
    }
}
