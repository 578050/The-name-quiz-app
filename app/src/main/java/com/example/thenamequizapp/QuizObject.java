package com.example.thenamequizapp;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizObject {

    private static Animal animalObj = new Animal();
    private static Animal chosenAnimal;
    private static int score = 0;
    private static int trY = 0;

    //Random selection of an animal & send image and store name

    public static Animal chosenAnimal(){

        ArrayList<Animal> animals = animalObj.getAnimals();
        Collections.shuffle(animals);


        chosenAnimal = animals.get(1);

        return chosenAnimal;

    }

    public static String chosenAnimalName(){
        return chosenAnimal.getName();
    }


    //Two random names from the list that are not the answer plus the answer

    public static ArrayList<String> getMultiChoiceNames(){

        ArrayList<String> animalNames = new ArrayList<>();
        ArrayList<Animal> animals = animalObj.getAnimals();

        chosenAnimal();
        String chosenName = chosenAnimal.getName();

        for(Animal animal: animals){
            if(animal.getName() != chosenName && animalNames.size() < 4){
                animalNames.add(animal.getName());
            }
        }

        animalNames.add(chosenName);

        Collections.shuffle(animalNames);


        return animalNames;

    }

    //Send Image
    public static byte[] getChosenImage(){
        return chosenAnimal.getBytes();
    }


    //Check if the answer is correct
    public static boolean correctAnswer(String answer){
        boolean correctAnswer = false;

        if(chosenAnimal.getName() == answer){
            correctAnswer = true;
        }

        return correctAnswer;
    }


}
