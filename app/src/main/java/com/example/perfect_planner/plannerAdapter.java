package com.example.perfect_planner;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class plannerAdapter extends RecyclerView.Adapter<plannerAdapter.PlannerViewHolder> {
    @NonNull
    @Override
    public PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlannerViewHolder holder, int position) {

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
