package com.example.perfect_planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    }

    public void addPleaseButton (View v){
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        startActivity(intent);
    }

    public void editButton(View v){
        //get which assignment was clicked
        String name = "tempString";
        int classIndex = 0;
        String date = "tempString";
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

    public void instruction(View v){
        Toast.makeText(getApplicationContext(),"Our app the Perfect Planner seeks to keep you organized.\nThis app will provide a place to store your assignments, the due date for said assignments, the details for assignments, and a place to mark it as done!", Toast.LENGTH_LONG).show();
    }
}