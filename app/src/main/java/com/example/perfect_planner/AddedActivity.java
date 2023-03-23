package com.example.perfect_planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_main);
        Button SaveBTN = findViewById(R.id.saveBTN);
        Button CancelBTN = findViewById(R.id.cancelBTN);
        SaveBTN.setOnClickListener(view ->{
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }
    public void createAssignment (View v){
        EditText assignName = findViewById(R.id.nameET);
        EditText catClass = findViewById(R.id.categoryET);
        EditText dueDate = findViewById(R.id.dueDateET);

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.putExtra("name", assignName.getText().toString());
        i.putExtra("category", catClass.getText().toString());
        i.putExtra("due date", dueDate.getText().toString());
        startActivity(i);
    }
}
