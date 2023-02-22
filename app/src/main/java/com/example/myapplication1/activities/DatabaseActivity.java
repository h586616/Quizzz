package com.example.myapplication1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.data.Animal;
import com.example.myapplication1.data.AnimalDAO;
import com.example.myapplication1.data.AnimalDB;
import com.example.myapplication1.data.SorterHelper;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AnimalDAO animalDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_animals_activity);

        recyclerView = findViewById(R.id.userRecyclerView);

        animalDAO = AnimalDB.getDBInstance(this).animalDAO();
        AnimalRecycler animalRecycler = new AnimalRecycler(animalDAO.getAllAnimals(), this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animalRecycler);
    }

    public void sortAlphabetically(View view){
        sortAnimals(true);
    }

    public void sortAlphabeticallyReversed(View view) {
        sortAnimals(false);
    }

    private void sortAnimals(boolean reverseOrder){
        animalDAO = AnimalDB.getDBInstance(this).animalDAO();
        List<Animal> animalList = animalDAO.getAllAnimals();

        SorterHelper sorterHelper = new SorterHelper(animalList);
        if (reverseOrder) {
            sorterHelper.sortAlphabeticallyReversed();
        } else {
            sorterHelper.sortAlphabetically();
        }
        AnimalRecycler animalRecycler = new AnimalRecycler(sorterHelper.getSortedAnimalList(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animalRecycler);
    }

    public void addAnimal(View v){
        startActivity(new Intent(DatabaseActivity.this, AddAnimalActivity.class));
    }

}
