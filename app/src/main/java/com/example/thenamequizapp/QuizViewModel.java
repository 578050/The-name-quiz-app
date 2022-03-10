package com.example.thenamequizapp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;



public class QuizViewModel  extends ViewModel {

    private int score = 0;
    private int trY = 0;
    private Animal chosenAnimal;
    private String chosenAnimalName;
    private ArrayList<String> MultiChoiceNames;
    private byte[] chosenImage;
    private boolean correctAnswer;


    public int getScore() {
        return score;
    }

    public int getTrY() {
        return trY;
    }

    public void addScore(){
        score++;
    }

    public void  addTry(){
        trY++;
    }


    public Animal getChosenAnimal() {
        return chosenAnimal;
    }

    public void setChosenAnimal(Animal chosenAnimal) {
        this.chosenAnimal = chosenAnimal;
    }

    public String getChosenAnimalName() {
        return chosenAnimalName;
    }

    public void setChosenAnimalName(String chosenAnimalName) {
        this.chosenAnimalName = chosenAnimalName;
    }

    public ArrayList<String> getMultiChoiceNames() {
        return MultiChoiceNames;
    }

    public void setMultiChoiceNames(ArrayList<String> multiChoiceNames) {
        MultiChoiceNames = multiChoiceNames;
    }

    public byte[] getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(byte[] chosenImage) {
        this.chosenImage = chosenImage;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
