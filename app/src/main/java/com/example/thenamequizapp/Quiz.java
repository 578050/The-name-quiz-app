package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Quiz extends AppCompatActivity {

    private ListView listView;
    private QuizAdapter adapter;

    private ImageView imageView;
    private MutableLiveData<ArrayList<String>> animalNames;
    private int score;
    private int trY;

    private QuizViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        score = viewModel.getScore();
        trY = viewModel.getTrY();

        animalNames = viewModel.getMultiChoiceNames();
        ArrayList<String> animalNames2 = animalNames.getValue();

        listView = (ListView) findViewById(R.id.quizList);
        imageView = (ImageView) findViewById(R.id.quizImage);
        TextView scoreTextView = (TextView) findViewById(R.id.score);

        scoreTextView.setText("Score: " + score + "/" + trY);

        adapter = new QuizAdapter(this, R.layout.quiz_list_raw, animalNames2);

        LiveData<byte[]> bytes = viewModel.getChosenImage();
        Bitmap image = BitmapFactory.decodeByteArray(bytes.getValue(), 0, bytes.getValue().length);
        imageView.setImageBitmap(image);


        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = animalNames2.get(i);
                MutableLiveData<Boolean> answer = viewModel.isCorrectAnswer(name);
                MutableLiveData<String> rightName = viewModel.getChosenAnimalName();


                if(answer.getValue()){
                    Toast.makeText(getApplicationContext(), "You are correct!!!!",Toast.LENGTH_SHORT).show();
                    viewModel.addScore();
                    score = viewModel.getScore();
                    viewModel.addTry();
                    trY = viewModel.getTrY();
                }else{
                    Toast.makeText(getApplicationContext(), "Oops!  the correct answer is: " + rightName.getValue(),Toast.LENGTH_LONG).show();
                    viewModel.addTry();
                    trY = viewModel.getTrY();

                }


                scoreTextView.setText("Score: " + score + "/" + trY);



                viewModel.chosen = false;
                LiveData<byte[]> bytes = viewModel.getChosenImage();

                viewModel.mulitpleChoiseSet = false;
                animalNames = viewModel.getMultiChoiceNames();

                Bitmap image = BitmapFactory.decodeByteArray(bytes.getValue(), 0, bytes.getValue().length);
                imageView.setImageBitmap(image);


                QuizAdapter adapter2 = new QuizAdapter(Quiz.this, R.layout.quiz_list_raw, animalNames.getValue());

                listView.setAdapter(adapter2);

            }
        });

    }

}