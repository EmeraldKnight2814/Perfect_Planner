package com.example.perfect_planner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder> {

    public static class PlannerViewHolder extends RecyclerView.ViewHolder{
        public PlannerViewHolder(View v) {super(v);}
    }

    public PlannerAdapter(){
        super();
    }

    @NonNull
    @Override
    public PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assigntemp, parent, false);
        PlannerViewHolder th= new PlannerViewHolder(v);
        return th;
    }

    private Model myModel = Model.getModel();
    @Override
    public void onBindViewHolder(@NonNull PlannerViewHolder holder, int position) {
        TextView assignTV = holder.itemView.findViewById(R.id.assignTV);
        TextView dueDateTV = holder.itemView.findViewById(R.id.dueDateTV);
        assignTV.setText(myModel.getAsgmtList().get(position).getAsgmt());
        dueDateTV.setText(Model.getModel().getAsgmtList().get(position).getDate()+"");

    }

    @Override
    public int getItemCount() {
        return myModel.getAsgmtList().size();
    }

    public void removeItem(int position) {
        myModel.getAsgmtList().remove(position);
        notifyItemRemoved(position);
    }
}
