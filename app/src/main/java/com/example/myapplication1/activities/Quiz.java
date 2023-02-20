package com.example.myapplication1.activities;

public class Quiz {

    private int image;
    private String name;
    private String correctAnswer;
    private String answer2;
    private String answer3;

    public Quiz(int image, String name, String correctAnswer, String answer2, String answer3) {
        this.image = image;
        this.name = name;
        this.correctAnswer = correctAnswer;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }
}
