package com.example.myapplication1.data;

public class Score {
    private int score;
    private int attempts;

    public void incrementScore() {
        score++;
    }

    public void incrementAttempts() {
        attempts++;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    @Override
    public String toString() {
        return score + " | " + attempts;
    }

}
