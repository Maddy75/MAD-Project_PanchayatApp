package com.example.mypanchayat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypanchayat.Models.Requests;
import com.example.mypanchayat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    ArrayList<Requests> list;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Context context;

    public ApplicationAdapter(ArrayList<Requests> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_application_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.ViewHolder holder, int position) {
        Requests model = list.get(position);

        holder.etName.setText(model.getUserName());
        holder.etArea.setText(model.getVillageName() + ", " + model.getWardName().subSequence(0,4) + model.getWardName().subSequence(8,10));
        holder.etPhone.setText(model.getUserPhoneNo());
        holder.etService.setText(model.getRequestCategory());
        holder.etRequestNo.setText("Application " + (position+1));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView etName, etArea, etPhone, etService, etRequestNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            etName = itemView.findViewById(R.id.etName);
            etArea = itemView.findViewById(R.id.etAreaName);
            etPhone = itemView.findViewById(R.id.etPhoneNo);
            etService = itemView.findViewById(R.id.etServiceType);
            etRequestNo = itemView.findViewById(R.id.etRequestNumber);
        }

    }
}
