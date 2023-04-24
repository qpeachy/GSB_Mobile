package com.example.gsbmobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsbmobile.Models.Doctor;
import com.example.gsbmobile.R;

import java.util.List;

public class RecyclerViewAdapterDoctors extends RecyclerView.Adapter<RecyclerViewAdapterDoctors.RecyclerViewHolder> {
    private List<Doctor> dataModelList;

    public RecyclerViewAdapterDoctors(List<Doctor> dataModelList){this.dataModelList=dataModelList;}


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_recyclerview_list_item, parent, false);
        viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvDoctorName.setText(String.valueOf(dataModelList.get(position).getName()));
        holder.tvDoctorSurname.setText(String.valueOf(dataModelList.get(position).getSurname()));
    }

    public long getItemId(int position){
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {return dataModelList.size();}

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvDoctorName;
        TextView tvDoctorSurname;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvDoctorSurname = itemView.findViewById(R.id.tvDoctorSurname);
            //commentai
        }
    }
}
