package com.example.perfect_planner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlannerAdapter plannerServer = new PlannerAdapter();
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        plannerRV.setAdapter(plannerServer);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        plannerRV.setLayoutManager(manage);

        Spinner filters = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        filters.setAdapter(adapter);

        SharedPreferences sh = getSharedPreferences("SharedPref", MODE_PRIVATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myedit = sharedPreferences.edit();
        int count = Model.getModel().getAsgmtList().size();

        for (int i = 0; i < count; i++) {
            ArrayList<Model.Asgmt> assignments = Model.getModel().getAsgmtList();
            Spinner filters = findViewById(R.id.filter);
            ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) filters.getAdapter();
            String name = assignments.get(i).getAsgmt();
            int classIndex = adapter.getPosition(assignments.get(i).getCat());
            String date = assignments.get(i).getDate();
            int index = i;
            myedit.putString("name", name);
            myedit.putInt("class", classIndex);
            myedit.putString("date", date);
            myedit.putInt("index", index);
        }
    }

    public void addPleaseButton (View v){
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        startActivity(intent);
    }

    public void editButton(View v){
        //get which assignment was clicked
        ArrayList<Model.Asgmt> assignments = Model.getModel().getAsgmtList();
        Spinner filters = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) filters.getAdapter();
        String name = assignments.get(0).getAsgmt();
        int classIndex = adapter.getPosition(assignments.get(0).getCat());
        String date = assignments.get(0).getDate();
        int index = 0;

        //pass data to added activity
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("class", classIndex);
        intent.putExtra("date", date);
        intent.putExtra("index", index);

        //launch added activity
        startActivity(intent);
    }

    public void removeButton(View v){
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        View view = plannerRV.findChildViewUnder(v.getX(), v.getY());
        RecyclerView.ViewHolder holder = plannerRV.getChildViewHolder(view);
        int position = holder.getAdapterPosition();
        Model.getModel().removeItem(position);
    }

    public void instruction(View v){
        Toast.makeText(getApplicationContext(),"Our app the Perfect Planner seeks to keep you organized.\nThis app will provide a place to store your assignments, the due date for said assignments, the details for assignments, and a place to mark it as done!", Toast.LENGTH_LONG).show();
    }
}