package com.example.myapplication1.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Randomizer {

    private Animal previousAnimal;
    private Animal correctAnimal;
    private List<Animal> animalList;

    public Randomizer(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Animal> makeAnswerOptions() {
        List<Animal> answerOptionsList = new ArrayList<>();
        answerOptionsList.add(this.correctAnimal);

        int indexOfCorrectAnswer = animalList.indexOf(this.correctAnimal);
        int indexOfOption2 = 0;
        int indexOfOption3 = 0;

        if (indexOfCorrectAnswer == animalList.size() - 1) {
            indexOfOption2 = indexOfCorrectAnswer - 1;
            indexOfOption3 = indexOfCorrectAnswer - 2;

        } else if (indexOfCorrectAnswer == 0) {
            indexOfOption2 = indexOfCorrectAnswer + 1;
            indexOfOption3 = indexOfCorrectAnswer + 2;

        } else {
            indexOfOption2 = indexOfCorrectAnswer + 1;
            indexOfOption3 = indexOfCorrectAnswer - 1;
        }

        Animal option2 = animalList.get(indexOfOption2);
        Animal option3 = animalList.get(indexOfOption3);
        answerOptionsList.add(option2);
        answerOptionsList.add(option3);

        Collections.shuffle(answerOptionsList);
        return answerOptionsList;
    }

    public Animal generateNewAnimal() {
        int index = generateRandomIndex();

        if (index == animalList.indexOf(previousAnimal)) {
            index = generateRandomIndex();
        }

        this.correctAnimal = animalList.get(index);
        setPreviousAnimal(correctAnimal);

        return this.correctAnimal;
    }
    // Private helper methods
    private int generateRandomIndex() {
        return 0 + (int)(Math.random() * (animalList.size()));
    }

    private void setPreviousAnimal(Animal animal) {
        this.previousAnimal = animal;
    }

    private Animal getPreviousAnimal() {
        return this.previousAnimal;
    }


}
