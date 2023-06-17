package com.example.mypanchayat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypanchayat.Models.RationModel;
import com.example.mypanchayat.Models.Requests;
import com.example.mypanchayat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RationAdapter extends RecyclerView.Adapter<RationAdapter.ViewHolder> {

    ArrayList<RationModel> list;
    Context context;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    public RationAdapter(ArrayList<RationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_ration_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RationAdapter.ViewHolder holder, int position) {

        RationModel model = list.get(position);

        holder.tvItemCount.setText("Item - " + (position+1));
        holder.tvItemAvailability.setText("Available!!!");
        holder.tvItemName.setText(model.getItemName());

        long validityTimeStamp = model.getRationValidityTime();
        // Create a Date object from the timestamp
        Date date = new Date(validityTimeStamp);

        // Format the date as desired
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);

        holder.tvItemValidity.setText(formattedDate);
        holder.tvItemQuantity.setText(model.getRationQuantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemCount, tvItemName, tvItemAvailability, tvItemQuantity, tvItemValidity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItemCount = itemView.findViewById(R.id.tvItemCount);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemAvailability = itemView.findViewById(R.id.tvAvailability);
            tvItemValidity = itemView.findViewById(R.id.tvValidity);
            tvItemQuantity = itemView.findViewById(R.id.tvQuantity);

        }

    }
}
