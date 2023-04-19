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
import android.util.Log;
import android.view.MotionEvent;
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
        
        try {
            PlannerAdapter plannerServer = new PlannerAdapter();
            RecyclerView plannerRV = findViewById(R.id.assignRV);
            plannerRV.setAdapter(plannerServer);
            LinearLayoutManager manage = new LinearLayoutManager(this);
            plannerRV.setLayoutManager(manage);
        } catch (Exception e){
            e.printStackTrace();
            // handle the error
        }
        Spinner filters = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        filters.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("SharedPref", MODE_PRIVATE);
        int j = sh.getInt("NUMBEROFITERATIONS", 0);
        for (int i = 0; i < j; i++) {
            String assigment = sh.getString("name" + String.valueOf(i), "");
            String date = sh.getString("date" + String.valueOf(i), "");
            String classIndex = sh.getString("class" + String.valueOf(i), "");
            Model.Asgmt asgmnt = new Model.Asgmt(assigment, date, classIndex);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myedit = sharedPreferences.edit();
            int count = Model.getModel().getAsgmtList().size();
            ArrayList<Model.Asgmt> assignments = Model.getModel().getAsgmtList();
            Spinner filters = findViewById(R.id.filter);
            ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) filters.getAdapter();
            int j = 0;
            for (int i = 0; i < count; i++) {
                j = i;
                String name = assignments.get(i).getAsgmt();
                String classIndex = assignments.get(i).getCat();
                String date = assignments.get(i).getDate();
                int index = i;
                myedit.putString("name" + String.valueOf(i), name);
                myedit.putString("class" + String.valueOf(i), classIndex);
                myedit.putString("date" + String.valueOf(i), date);
                myedit.putInt("index" + String.valueOf(i), index);
            }

            myedit.putInt("NUMBEROFITERATIONS", j);
            myedit.commit();
        } catch (Exception e){
            e.printStackTrace();
            // handle the error
        }
    }

    public void addPleaseButton (View v){
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        startActivity(intent);
    }

    public void instructionButton (View v){
        Intent intent = new Intent(getApplicationContext(), InstructionActivity.class);
        startActivity(intent);
    }

    public void editButton(View v){
        try {
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
        } catch (Exception e){
            e.printStackTrace();
            // handle the error
        }
    }

    /*
    public void removeButton(View v){
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        Model.getModel().removeItem(position);
    }
     */

    public void recycle(View v){
        Intent recycle = new Intent(getApplicationContext(), recently_deleted.class);
        startActivity(recycle);
    }

}