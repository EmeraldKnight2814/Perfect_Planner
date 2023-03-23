package com.example.perfect_planner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner filters = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        filters.setAdapter(adapter);

        Bundle stats = getIntent().getExtras();
        if(stats != null){
            String tempName = stats.getString("name");
            String tempCat = stats.getString("category");
            String tempDate = stats.getString("due date");
            //if class is in model
            if(Model.getModel().getAsgmtList().contains(tempName)){
                // get index of class
                int index = Model.getModel().getAsgmtList().indexOf(tempName);
                // change class stats
                //insert
            } else{
                // add class to model
                //insert
            }
        }

        /*mStartForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();

                            display();
                        }
                    }
                }
        );*/
    }

    public void addPleaseButton (View v){
        Intent intent = new Intent(getApplicationContext(), AddedActivity.class);
        startActivity(intent);
    }
}