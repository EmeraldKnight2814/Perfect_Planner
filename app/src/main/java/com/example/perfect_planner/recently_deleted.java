package com.example.perfect_planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class recently_deleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_deleted);

        DelAdapter delServer = new DelAdapter();
        RecyclerView delRV = findViewById(R.id.deletedRV);
        delRV.setAdapter(delServer);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        delRV.setLayoutManager(manage);
    }

    public void returnToMain(View v){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    public void restoreAll(View v){
        DelModel.getModel().restoreAll();
    }

    public void clearAll(View v){
        DelModel.getModel().clear();
    }

    public void restore(View v){
        int index = 0;
        DelModel.getModel().restoreItem(index);
    }

    public void delete(View v){
        int index = 0;
        DelModel.getModel().removeItem(index);
    }
}