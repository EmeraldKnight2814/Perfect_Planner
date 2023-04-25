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
import java.util.Map;

import org.json.*;

public class MainActivity extends AppCompatActivity implements MainButtons {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PlannerAdapter plannerServer = new PlannerAdapter(this, this);
            RecyclerView plannerRV = findViewById(R.id.assignRV);
            plannerRV.setAdapter(plannerServer);
            LinearLayoutManager manage = new LinearLayoutManager(this);
            plannerRV.setLayoutManager(manage);
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_LONG).show();
        }

        Spinner filters = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        filters.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String jsonData = sharedPreferences.getString("Data", null);

        if (jsonData != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("assignments");
                ArrayList<Model.Asgmt> assignments = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject assignmentObject = jsonArray.getJSONObject(i);
                    String title = assignmentObject.getString("title");
                    String description = assignmentObject.getString("cat");
                    String dueDate = assignmentObject.getString("date");
                    Model.Asgmt asgmt = new Model.Asgmt(title, description, dueDate);
                    assignments.add(asgmt);
                    Log.d("Loaded Data", jsonData);
                }
                Model.getModel().setAsgmtList(assignments);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*plannerRV.setAdapter(adapter);
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
        PlannerAdapter adapter = new PlannerAdapter(savedData, getApplicationContext(), this);
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        plannerRV.setAdapter(adapter);*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<Model.Asgmt> assignments = Model.getModel().getAsgmtList();
        JSONArray jsonArray = new JSONArray();
        for (Model.Asgmt asgmt : assignments) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("title", asgmt.getAsgmt());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObject.put("cat", asgmt.getCat());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObject.put("date", asgmt.getDate());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        String jsonData = null;
        try {
            jsonData = new JSONObject().put("assignments", jsonArray).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("Saved Data", jsonData);
        editor.putString("Data", jsonData);
        editor.apply();
        /*try {
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
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
        }

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
        }*/
    }

    public void addPleaseButton (View v){
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        startActivity(intent);
    }

    public void instructionButton (View v){
        Intent intent = new Intent(this, InstructionActivity.class);
        startActivity(intent);
    }

    @Override
    public void editButton(int position){
        try {
            //get which assignment was clicked
            ArrayList<Model.Asgmt> assignments = Model.getModel().getAsgmtList();
            Spinner filters = findViewById(R.id.filter);
            ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) filters.getAdapter();
            String name = assignments.get(position).getAsgmt();
            int classIndex = adapter.getPosition(assignments.get(position).getCat());
            String date = assignments.get(position).getDate();
            int index = position;

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

    @Override
    public void removeButton(int position){
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        PlannerAdapter plannerServer = (PlannerAdapter)plannerRV.getAdapter();
        Model.getModel().removeItem(position);
        plannerServer.notifyDataSetChanged();
    }

    public void recycle(View v){
        Intent recycle = new Intent(getApplicationContext(), recently_deleted.class);
        startActivity(recycle);
    }

    public void clearTask(View v){
        RecyclerView plannerRV = findViewById(R.id.assignRV);
        Model.getModel().clear();
        plannerRV.getAdapter().notifyDataSetChanged();
    }

}