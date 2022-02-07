package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button database_button;
    private Button quiz_button;
    private Button addEntry_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database_button = findViewById(R.id.database);
        quiz_button = findViewById(R.id.quiz);
        addEntry_button = findViewById(R.id.addEntry);

        database_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatabase();
            }
        });

        quiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuiz();
            }
        });

        addEntry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddEntry();
            }
        });
    }

    public void openDatabase(){
        Intent intent = new Intent(this, Database.class);
        startActivity(intent);
    }
    public void openQuiz(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }
    public void openAddEntry(){
        Intent intent = new Intent(this, AddEntry.class);
        startActivity(intent);
    }

}