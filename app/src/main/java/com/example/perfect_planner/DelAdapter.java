package com.example.perfect_planner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DelAdapter extends RecyclerView.Adapter<DelAdapter.DelViewHolder> {

    public static class DelViewHolder extends RecyclerView.ViewHolder{
        public DelViewHolder(View v) {super(v);}
    }

    public DelAdapter(){
        super();
    }

    @NonNull
    @Override
    public DelAdapter.DelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assigntemp, parent, false);
        DelAdapter.DelViewHolder th= new DelAdapter.DelViewHolder(v);
        return th;
    }

    private DelModel myModel = DelModel.getModel();
    @Override
    public void onBindViewHolder(@NonNull DelAdapter.DelViewHolder holder, int position) {
        TextView assignTV = holder.itemView.findViewById(R.id.assignTV);
        TextView dueDateTV = holder.itemView.findViewById(R.id.dueDateTV);
        assignTV.setText(myModel.getAsgmtList().get(position).getAsgmt());
        dueDateTV.setText(myModel.getModel().getAsgmtList().get(position).getDate()+"");
    }

    @Override
    public int getItemCount() {
        return myModel.getAsgmtList().size();
    }
}
