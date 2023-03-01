package com.example.perfect_planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_main);
        Button SaveBTN = findViewById(R.id.saveBTN);
        SaveBTN.setOnClickListener(view ->{
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }

    public class addActivity {

    }

}
