package com.example.myapplication1.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SorterHelper {

    private List<Animal> animalList;

    public SorterHelper(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public void sortAlphabetically() {
        Collections.sort(this.animalList, new Comparator<Animal>() {

            @Override
            public int compare(Animal a1, Animal a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortAlphabeticallyReversed() {
        this.animalList.sort(Comparator.comparing(Animal::getName, Comparator.reverseOrder()));
    }

    public List<Animal> getSortedAnimalList() {
        return this.animalList;
    }
}
