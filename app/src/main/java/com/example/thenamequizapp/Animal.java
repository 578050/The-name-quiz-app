package com.example.thenamequizapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Animal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image")
    private byte[] bytes;

    @Ignore
    private Bitmap image;
    @Ignore
    private AppDatabase db;

    private static ArrayList<Animal> animals = new ArrayList<>();


    public Animal(){

    }
    public Animal(int id, String name, byte[] bytes){
        this.id = id;
        this.name = name;
        this.bytes = bytes;
    }

    public Animal(int id, String name, Bitmap image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public byte[] getBytes () {

        return bytes;
    }

    public void setBytes(byte[] bytes) {

        this.bytes = bytes;
    }

    public Bitmap getImage(){
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void addAnimal(Animal animal){

        if(!animals.contains(animal)){
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

    public ArrayList<Animal> getAll() {
        return animals;
    }

    public int createId(){
        List<Animal> list = db.AnimalDao().getAll();
        int id = list.get(list.size()-1).getId();
        return id++;
    }

}
