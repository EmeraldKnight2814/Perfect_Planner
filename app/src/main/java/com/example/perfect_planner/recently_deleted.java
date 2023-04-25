package com.example.perfect_planner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class recently_deleted extends AppCompatActivity implements ReDelButtons {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_deleted);

        DelAdapter delServer = new DelAdapter(this, this);
        RecyclerView delRV = findViewById(R.id.deletedRV);
        delRV.setAdapter(delServer);
        LinearLayoutManager manage = new LinearLayoutManager(this);
        delRV.setLayoutManager(manage);
    }

    // Return to the Main Activity
    public void returnToMain(View v){
        finish();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    // Restore all the assignments in the trash can
    public void restoreAll(View v){
        RecyclerView delRV = findViewById(R.id.deletedRV);
        DelAdapter delServer = (DelAdapter)delRV.getAdapter();
        DelModel.getModel().restoreAll();
        delServer.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Assignments restored", Toast.LENGTH_LONG).show();
    }

    // Clear all the assignments in the trash can
    public void clearAll(View v){
        RecyclerView delRV = findViewById(R.id.deletedRV);
        DelAdapter delServer = (DelAdapter)delRV.getAdapter();
        DelModel.getModel().clear();
        delServer.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"All assignments cleared", Toast.LENGTH_LONG).show();
    }

    @Override
    public void restore(int index){
        RecyclerView delRV = findViewById(R.id.deletedRV);
        DelAdapter delServer = (DelAdapter)delRV.getAdapter();
        DelModel.getModel().restoreItem(index);
        delServer.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Assignment restored", Toast.LENGTH_LONG).show();
    }

    @Override
    public void delete(int index){
        RecyclerView delRV = findViewById(R.id.deletedRV);
        DelAdapter delServer = (DelAdapter)delRV.getAdapter();
        DelModel.getModel().removeItem(index);
        delServer.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"Assignment deleted", Toast.LENGTH_LONG).show();
    }

}