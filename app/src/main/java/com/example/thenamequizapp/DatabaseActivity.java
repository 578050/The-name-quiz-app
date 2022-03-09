package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.RoomDatabase;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private ListView listView;
    private AnimalAdapter adapter;
    private Animal animalObj = new Animal();
    private AppDatabase db;
    private boolean run = false;


    List<Animal> animalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        db = AppDatabase.getDatabase(this.getApplicationContext());
        Button sortA_Z = findViewById(R.id.sortA_Z);
        Button sortZ_A = findViewById(R.id.sortZ_A);
        listView = (ListView) findViewById(R.id.listView);

        runDatabase();  //This method is used to prepopulate the database if needed
        getAllAnimals();


        adapter = new AnimalAdapter(this, R.layout.database_list_row, animalObj.getAnimals());
        listView.setAdapter(adapter);


        sortA_Z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListA_Z();
            }
        });

        sortZ_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListZ_A();
            }
        });
    }


    public void getAllAnimals() {
        animalObj.getAnimals().clear();
        animalList = db.AnimalDao().getAll();
        for(Animal animal : animalList){
            animal = readyForDisplay(animal);
            animalObj.addAnimal(animal);
        }
    }


    public static byte[] convertToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,500,500,true);
        newBitmap.compress(Bitmap.CompressFormat.PNG, 0,stream);
        byte[] bitmapData = stream.toByteArray();

        return bitmapData;
    }

    public static Animal readyForDisplay(Animal animal){
        byte[] bytes = animal.getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        animal.setImage(bitmap);
        return animal;
    }

    private void runDatabase(){

        if(!run){

            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            byte[] catByte = convertToByteArray(bitmap1);

            Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
            byte[] dogByte = convertToByteArray(bitmap2);

            Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.elephant);
            byte[] elephantByte = convertToByteArray(bitmap3);


            Animal cat = new Animal(1, "Cat", catByte);
            Animal dog = new Animal(2, "Dog", dogByte);
            Animal elephant = new Animal(3, "Elephant", elephantByte);


            db.AnimalDao().insert(dog);
            db.AnimalDao().insert(cat);
            db.AnimalDao().insert(elephant);

            run = true;

        } else {
            run = true;
        }
    }
    private void sortArrayListA_Z(){

        ArrayList<Animal> animals = animalObj.getAnimals();

        Collections.sort(animals, new Comparator<Animal>() {
            @Override
            public int compare(Animal animal, Animal t1) {
                return animal.getName().compareTo(t1.getName());
            }
        });

        adapter.notifyDataSetChanged();

    }

    private void sortArrayListZ_A(){

        ArrayList<Animal> animals = animalObj.getAnimals();

        Collections.sort(animals, new Comparator<Animal>() {
            @Override
            public int compare(Animal animal, Animal t1) {
                return animal.getName().compareTo(t1.getName());
            }
        });

        Collections.reverse(animals);

        adapter.notifyDataSetChanged();

    }


}
