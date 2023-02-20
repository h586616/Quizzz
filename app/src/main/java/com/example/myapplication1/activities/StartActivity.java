package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.R;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        Button listOfAnimalsButton = findViewById(R.id.databaseButton);
        Button addAnimalButton = findViewById(R.id.addAnimalButton);
        Button startQuizButton = findViewById(R.id.start_button);


        listOfAnimalsButton.setOnClickListener(view -> startActivity(
                new Intent(StartActivity.this,
                        DatabaseActivity.class)));
        addAnimalButton.setOnClickListener(view -> startActivity(
                new Intent(StartActivity.this,
                        AddAnimalActivity.class)));
        startQuizButton.setOnClickListener(view -> startActivity(
                new Intent(StartActivity.this,
                        Mainactivity.class)));


    }
}