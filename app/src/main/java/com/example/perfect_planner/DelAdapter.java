package com.example.perfect_planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DelAdapter extends RecyclerView.Adapter<DelAdapter.DelViewHolder> {

    private Context mContext;
    private ReDelButtons buttons;

    public static class DelViewHolder extends RecyclerView.ViewHolder{
        int position;
        private Context mContext;
        private ReDelButtons buttons;
        public DelViewHolder(View v, Context context, ReDelButtons buttonsIn) {
            super(v);
            this.mContext = context;
            this.buttons = buttonsIn;
            //set edit button on-click listener
            v.findViewById(R.id.restoreBTN).setOnClickListener(view ->{
                position = getAdapterPosition();
                System.out.println();
                System.out.println("Restore Clicked at position: " + position);
                System.out.println();
                buttons.restore(position);
            });
            //set remove button on-click listener
            v.findViewById(R.id.removeBTN).setOnClickListener(view ->{
                position = getAdapterPosition();
                System.out.println();
                System.out.println("Delete Clicked at position: " + position);
                System.out.println();
                buttons.delete(position);
            });
        }
    }

    public DelAdapter(Context context, ReDelButtons buttonsIn){
        this.mContext = context;
        this.buttons = buttonsIn;
    }

    @NonNull
    @Override
    public DelAdapter.DelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deleted_assign, parent, false);
        DelAdapter.DelViewHolder th= new DelAdapter.DelViewHolder(v, mContext, buttons);
        return th;
    }

    private DelModel myModel = DelModel.getModel();
    @Override
    public void onBindViewHolder(@NonNull DelAdapter.DelViewHolder holder, int position) {
        TextView assignTV = holder.itemView.findViewById(R.id.delAssignTV);
        TextView dueDateTV = holder.itemView.findViewById(R.id.delDateTV);
        assignTV.setText(myModel.getAsgmtList().get(position).getAsgmt());
        dueDateTV.setText(myModel.getModel().getAsgmtList().get(position).getDate()+"");
    }

    @Override
    public int getItemCount() {
        return myModel.getAsgmtList().size();
    }
}
