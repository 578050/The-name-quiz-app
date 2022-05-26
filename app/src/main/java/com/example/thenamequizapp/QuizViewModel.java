package com.example.thenamequizapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class QuizViewModel  extends ViewModel {

    private int score = 0;
    private int trY = 0;
    private MutableLiveData<Animal> chosenAnimal = new MutableLiveData<>();
    private MutableLiveData<String> chosenAnimalName = new MutableLiveData<>();
    private MutableLiveData<ArrayList<String>> MultiChoiceNames = new MutableLiveData<>();
    private MutableLiveData<byte[]> chosenImage = new MutableLiveData<>();
    private MutableLiveData<Boolean> correctAnswer = new MutableLiveData<>();
    boolean chosen = false;
    boolean mulitpleChoiseSet = false;


    public int getScore() {
        return score;
    }

    public int getTrY() {
        return trY;
    }

    public void addScore() {
        score++;
    }

    public void addTry() {
        trY++;
    }


    public MutableLiveData<Animal> getChosenAnimal() {
        chosenAnimal.setValue(QuizObject.chosenAnimal());
        return chosenAnimal;
    }

    public MutableLiveData<String> getChosenAnimalName() {
        chosenAnimalName.setValue(QuizObject.chosenAnimalName());
        return chosenAnimalName;
    }


    public MutableLiveData<ArrayList<String>> getMultiChoiceNames() {
        if(!mulitpleChoiseSet){
            MultiChoiceNames.setValue(QuizObject.getMultiChoiceNames());
            mulitpleChoiseSet = true;
        }
        return MultiChoiceNames;
    }

    public LiveData<byte[]> getChosenImage() {
        if(!chosen){
            chosenImage.setValue(QuizObject.getChosenImage());
            chosen = true;
        }
        return chosenImage;
    }

    public MutableLiveData<Boolean> isCorrectAnswer(String answer) {
        correctAnswer.setValue(QuizObject.correctAnswer(answer));
        return correctAnswer;
    }
}
