package com.example.perfect_planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_main);
        Button SaveBTN = findViewById(R.id.saveBTN);
        Button CancelBTN = findViewById(R.id.cancelBTN);
        CancelBTN.setOnClickListener(view ->{
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }
    public void createAssignment (View v){
        EditText assignName = findViewById(R.id.nameET);
        Spinner catClass = findViewById(R.id.categoryET);
        EditText dueDate = findViewById(R.id.dueDateET);

        Model.Asgmt asgmnt = new Model.Asgmt(assignName.getText().toString(), dueDate.getText().toString(), catClass.getSelectedItem().toString());
        ArrayList<Model.Asgmt> list = Model.getModel().getAsgmtList();

        boolean contained = false;
        int indexC = 0;
        for(int i = 0; i < list.size(); i++){
            if(assignName.getText().toString() == list.get(i).getAsgmt()){
                contained = true;
                indexC = i;
                break;
            }
        }
        // If array list contains class already
        if(contained){
            //overwrite class
            list.set(indexC, asgmnt);
        }
        else{
            //add class
            list.add(asgmnt);
        }
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
