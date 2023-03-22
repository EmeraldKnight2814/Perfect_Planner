package com.example.perfect_planner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class plannerAdapter extends RecyclerView.Adapter<plannerAdapter.PlannerViewHolder> {
    @NonNull
    @Override
    public PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assigntemp, parent, false);
        PlannerViewHolder th= new PlannerViewHolder(v);
        return th;
    }

    @Override
    public void onBindViewHolder(@NonNull PlannerViewHolder holder, int position) {
        TextView assignTV = holder.itemView.findViewById(R.id.assignTV);
        TextView dueDateTV = holder.itemView.findViewById(R.id.dueDateTV);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PlannerViewHolder extends RecyclerView.ViewHolder{

        public PlannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
