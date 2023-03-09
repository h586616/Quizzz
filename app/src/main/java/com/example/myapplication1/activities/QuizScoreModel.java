package com.example.myapplication1.activities;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizScoreModel extends ViewModel {

    private MutableLiveData<String> scoreData;

    public MutableLiveData<String> getScoreData() {
        if (scoreData == null) {
            scoreData = new MutableLiveData<>();
        }
        return scoreData;
    }

    public void setScore(int score, int attempts) {
        scoreData.setValue(score + "/" + attempts);
    }
}

//subclass here i guess
