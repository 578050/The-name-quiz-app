package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database extends AppCompatActivity {

    private ListView listView;
    private AnimalAdapter adapter;
    private Animal animalObj = new Animal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

            listView = (ListView) findViewById(R.id.listView);


            adapter = new AnimalAdapter(this, R.layout.database_list_row, animalObj.getAnimals());
            listView.setAdapter(adapter);

            Button sortA_Z = findViewById(R.id.sortA_Z);
            Button sortZ_A = findViewById(R.id.sortZ_A);


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
