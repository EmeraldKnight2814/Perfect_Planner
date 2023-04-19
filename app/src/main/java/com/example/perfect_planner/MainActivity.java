package com.example.perfect_planner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Model.Asgmt> savedData = null;
        Log.d("MyApp", "Looking for data file...");
        try {
            FileInputStream fis = openFileInput("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedData = (ArrayList<Model.Asgmt>) ois.readObject();
            ois.close();
            fis.close();
            Log.d("MyApp", "Loaded " + savedData.size() + " items from file...");
            if (savedData != null && !savedData.isEmpty()) {
                Log.d("MyApp", "First item: " + savedData.get(0).toString());
            } else {
                Log.d("MyApp", "No data loaded from file...");
            }        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        PlannerAdapter adapter = new PlannerAdapter(savedData);
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        plannerRV.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<Model.Asgmt> data = Model.getModel().getAsgmtList();
        Log.d("MyApp", "Saving " + data.size() + " items to file...");
        Log.d("MyApp", "Saving data to file: " + getFilesDir().getAbsolutePath() + "/data.ser");
        try{
            FileOutputStream fos = openFileOutput("data.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
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
            Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_LONG).show();
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