package com.example.perfect_planner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

    public class InstructionActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.instruction_main);

            /*TextView displayTV = findViewById(R.id.displayTV);
            displayTV.setText("App Instruction:\nOur app the Perfect Planner seeks to keep you organized." +
                    "\nThis app will provide a place to store your assignments," +
                    "the due date for said assignments, the details for assignments, and a place to mark it as done!");
*/
        }

        public void returnButton (View v){
            finish();
        }
    }