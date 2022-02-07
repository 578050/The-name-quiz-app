package com.example.thenamequizapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class Animal {

    private String name;
    private Bitmap imageId;
    private static ArrayList<Animal> animals = new ArrayList<>();

    public Animal(){

    }
    public Animal(String name, Bitmap imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Bitmap getImageBitmap() {

        return imageId;
    }

    public void setImageId(Bitmap imageId) {

        this.imageId = imageId;
    }

    public void addAnimal(Animal animal){

        if(animals.contains(animal)){

        } else {
            animals.add(animal);
        }

    }

    public ArrayList<String> getAllNames(){

        ArrayList<String> names = null;

        for(Animal animal: animals){
            names.add(animal.getName());
        }

        return names;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void removeAnimal(Animal animal) {
            animals.remove(animal);
    }

}
