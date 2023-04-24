package com.example.gsbmobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsbmobile.Models.Visit;
import com.example.gsbmobile.R;

import java.util.ArrayList;

public class RecyclerViewAdapterVisits  extends RecyclerView.Adapter<RecyclerViewAdapterVisits.RecyclerViewHolder> {
    private ArrayList<Visit> dataModelList;

    public RecyclerViewAdapterVisits(ArrayList<Visit> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visits_recyclerview_list_item, parent, false);
        viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvDate.setText((String.valueOf(dataModelList.get(position).getDate())));
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
