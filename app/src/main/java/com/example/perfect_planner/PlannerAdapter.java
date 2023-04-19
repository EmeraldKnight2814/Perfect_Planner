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
    private ArrayList<Model.Asgmt> mData;

    public PlannerAdapter(ArrayList<Model.Asgmt> data, Context context){
        mData = data;
        this.mContext = context;
    }
    public static class PlannerViewHolder extends RecyclerView.ViewHolder{
        int position;
        private Context mContext;
        public PlannerViewHolder(View v, Context context) {
            super(v);
            this.mContext = context;
            //set edit button on-click listener
            v.findViewById(R.id.edit).setOnClickListener(view ->{
                if(mContext instanceof MainActivity){
                    if(mContext instanceof MainActivity){
                        ((MainActivity)mContext).editButton(position);
                    }
                }
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
        PlannerViewHolder th= new PlannerViewHolder(v, mContext);
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
