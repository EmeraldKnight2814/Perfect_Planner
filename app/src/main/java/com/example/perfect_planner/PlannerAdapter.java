package com.example.perfect_planner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder> {
    private Context mContext;
    private MainButtons buttons;

    public PlannerAdapter(Context context, MainButtons buttonsIn){
        this.mContext = context;
        this.buttons = buttonsIn;
    }
    public static class PlannerViewHolder extends RecyclerView.ViewHolder{
        int position;
        private Context mContext;
        private MainButtons buttons;
        public PlannerViewHolder(View v, Context context, MainButtons buttonsIn) {
            super(v);
            this.mContext = context;
            this.buttons = buttonsIn;
            //set edit button on-click listener
            v.findViewById(R.id.edit).setOnClickListener(view ->{
                position = getAdapterPosition();
                System.out.println();
                System.out.println("Edit Clicked at position: " + position);
                System.out.println();
                buttons.editButton(position);
            });
            //set remove button on-click listener
            v.findViewById(R.id.remove).setOnClickListener(view ->{
                position = getAdapterPosition();
                System.out.println();
                System.out.println("Remove Clicked at position: " + position);
                System.out.println();
                buttons.removeButton(position);
            });
        }
    }

    public PlannerAdapter(){
        super();
    }

    @NonNull
    @Override
    public PlannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assigntemp, parent, false);
        PlannerViewHolder th= new PlannerViewHolder(v, mContext, buttons);
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
