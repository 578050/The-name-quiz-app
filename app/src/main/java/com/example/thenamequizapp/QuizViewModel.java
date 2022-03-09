package com.example.thenamequizapp;

import androidx.lifecycle.ViewModel;


public class QuizViewModel  extends ViewModel {

    private int score;
    private int trY;


    public int getScore() {
        return score;
    }

    public int getTrY() {
        return trY;
    }

    public void setTrY(int trY) {
        this.trY = trY;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int addScore(){
        return score++;
    }

    public int addTry(){
        return trY++;
    }
}
