package com.example.perfect_planner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AddedActivity extends AppCompatActivity {
    private boolean edit = false;
    private int editIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_main);
        Button SaveBTN = findViewById(R.id.saveBTN);
        Button CancelBTN = findViewById(R.id.cancelBTN);
        Button datePicker = findViewById(R.id.datePickerBTN);
        TextView dateTV = findViewById(R.id.dateTV);
        String date = "";
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddedActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        CancelBTN.setOnClickListener(view ->{
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
        // if EDIT was clicked
        Bundle stats = getIntent().getExtras();
        if(stats != null){
            edit = true;
            EditText assignName = findViewById(R.id.nameET);
            Spinner catClass = findViewById(R.id.categoryET);

            //set current text values to assignment we wish to edit
            assignName.setText(stats.getCharSequence("name"));
            catClass.setSelection(stats.getInt("class"));
            dateTV.setText(stats.getCharSequence("date"));
            editIndex = stats.getInt("index");
        }
    }
    public void createAssignment (View v){
        EditText assignName = findViewById(R.id.nameET);
        Spinner catClass = findViewById(R.id.categoryET);
        TextView dateTV = findViewById(R.id.dateTV);

        Model.Asgmt asgmnt = new Model.Asgmt(assignName.getText().toString(), dateTV.getText().toString(), catClass.getSelectedItem().toString());
        ArrayList<Model.Asgmt> list = Model.getModel().getAsgmtList();

        // If array list contains class already
        if(edit){
            //overwrite class
            list.set(editIndex, asgmnt);
        }
        else{
            //add class
            list.add(asgmnt);
        }
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
