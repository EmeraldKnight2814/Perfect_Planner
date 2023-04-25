package com.example.perfect_planner;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Startup extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
        String jsonData = sharedPreferences.getString("Data", "[]");

        if (jsonData != "[]") {
            try {
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("assignments");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject assignmentObject = jsonArray.getJSONObject(i);
                    String title = assignmentObject.getString("title");
                    String description = assignmentObject.getString("cat");
                    String dueDate = assignmentObject.getString("date");
                    Model.Asgmt asgmt = new Model.Asgmt(title, dueDate, description);
                    Model.getModel().getAsgmtList().add(asgmt);
                    Log.d("Loaded Data", jsonData);
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),"Something went wrong!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }

    }
}

