package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity {

    private ListView listView;
    private QuizAdapter adapter;

    private ImageView imageView;
    private ArrayList<String> animalNames;
    private int score;
    private int trY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);


        animalNames = QuizObject.getMultiChoiceNames();

        listView = (ListView) findViewById(R.id.quizList);
        imageView = (ImageView) findViewById(R.id.quizImage);

        adapter = new QuizAdapter(this, R.layout.quiz_list_raw, animalNames);

        imageView.setImageBitmap(QuizObject.getChosenImage());
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = animalNames.get(i);
                boolean answer = QuizObject.correctAnswer(name);
                TextView scoreTextView = (TextView) findViewById(R.id.score);
                String rightName = QuizObject.chosenAnimalName();


                if(answer){
                    Toast.makeText(getApplicationContext(), "You are correct!!!!",Toast.LENGTH_LONG).show();
                    score =QuizObject.addScore();
                    trY = QuizObject.addTry();
                }else{
                    Toast.makeText(getApplicationContext(), "Oops!  the correct answer is: " + rightName,Toast.LENGTH_LONG).show();
                    trY = QuizObject.addTry();
                }

                scoreTextView.setText("Score: " + score + "/" + trY);

                animalNames = QuizObject.getMultiChoiceNames();
                imageView.setImageBitmap(QuizObject.getChosenImage());

                QuizAdapter adapter2 = new QuizAdapter(Quiz.this, R.layout.quiz_list_raw, animalNames);

                listView.setAdapter(adapter2);

            }
        });



    }

}