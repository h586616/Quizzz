package com.example.myapplication1.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Query("SELECT * FROM Animals")
    List<Animal> getAllAnimals();

    @Insert
    void insertAnimal(Animal animal);

    @Update
    void updateAnimal(Animal animal);

    @Delete
    void deleteAnimal(Animal animal);
}

