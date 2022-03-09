package com.example.thenamequizapp;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface AnimalDao {

    @Query("SELECT * FROM animal")
    List<Animal> getAll();

    @Query("SELECT * FROM animal WHERE name LIKE :name")
    Animal findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Animal animal);


    @Delete
    void delete(Animal animal);
}
