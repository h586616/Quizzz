package com.example.myapplication1.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication1.activities.QuizScoreModel;
import com.example.myapplication1.data.Animal;
import com.example.myapplication1.data.AnimalDAO;
import com.example.myapplication1.data.AnimalDB;
import com.example.myapplication1.data.Converter;
import com.example.myapplication1.data.Randomizer;
import com.example.myapplication1.data.Score;
import com.example.myapplication1.R;


public class Mainactivity extends AppCompatActivity {


    //private static final int EASY_MODE_DURATION = 0; // 0 seconds
    //private static final int HARD_MODE_DURATION = 10; // 10 seconds
    private Animal correctAnimal;
    private QuizScoreModel model;
    AnimalDAO animalDAO;
    Score score;
    TextView resultView;
    TextView scoreView;
    Button onNext;

    private int modeDuration; // duration of the current quiz mode in seconds

    private CountDownTimer timer; // timer for hard mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        onNext = findViewById(R.id.nextQuestionButton);
        resultView = findViewById(R.id.result);
        scoreView =findViewById(R.id.score);


        score = new Score();

        model = new ViewModelProvider(this).get(QuizScoreModel.class);

        final Observer<String> scoreObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String score) {
                scoreView.setText(score);
            }
        };

        model.getScoreData().observe(this, scoreObserver);

        animalDAO = AnimalDB.getDBInstance(this).animalDAO();

        List<Animal> animalList = animalDAO.getAllAnimals();

        Randomizer randomizer = new Randomizer(animalList);

        ImageView imageView = findViewById(R.id.animalImage);
        Button answer1 = findViewById(R.id.answer_1);
        Button answer2 = findViewById(R.id.answer_2);
        Button answer3 = findViewById(R.id.answer_3);

        // Sett BC
        answer1.setBackgroundColor(getResources().getColor(R.color.white));
        answer2.setBackgroundColor(getResources().getColor(R.color.white));
        answer3.setBackgroundColor(getResources().getColor(R.color.white));

        correctAnimal = randomizer.generateNewAnimal();
        List<Animal> answerList = randomizer.makeAnswerOptions();
        imageView.setImageBitmap(Converter.convertByteArrayToImage(correctAnimal.getImage()));

        // Sett answers
        answer1.setText(answerList.get(0).getName());
        answer2.setText(answerList.get(1).getName());
        answer3.setText(answerList.get(2).getName());
/*
        switch (getUserSelectedMode()) {
            case "easy":
                modeDuration = EASY_MODE_DURATION;
                break;
            case "hard":
                modeDuration = HARD_MODE_DURATION;
                startTimer();
                break;
            default:
                modeDuration = EASY_MODE_DURATION;
        }
*/
        onClickAnswer(onNext, answer1, answer2, answer3, correctAnimal, score, model);

    }
/*
    private void startTimer() {
        timer = new CountDownTimer(modeDuration * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update timer display
                int secondsLeft = (int) millisUntilFinished / 1000;
                resultView.setText("Time left: " + secondsLeft + " seconds");
            }

            public void onFinish() {
                // Quiz ends when timer is done
                resultView.setText("Time's up!");
                disableAnswerButtons();
            }
        }.start();
    }
/*
    private void disableAnswerButtons() {
        onNext.setEnabled(false);
        findViewById(R.id.answer_1).setEnabled(false);
        findViewById(R.id.answer_2).setEnabled(false);
        findViewById(R.id.answer_3).setEnabled(false);
    }

  /*  private String getUserSelectedMode() {
        Button easyButton = findViewById(R.id.easy_button);
        Button hardButton = findViewById(R.id.hard_button);

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeDuration = EASY_MODE_DURATION;
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modeDuration = HARD_MODE_DURATION;
                startTimer();
            }
        });

        return modeDuration == EASY_MODE_DURATION ? "easy" : "hard";
    }*/

    public void onNextQuestion(View view) {
        List<Animal> animalList = animalDAO.getAllAnimals();
        Randomizer randomizer = new Randomizer(animalList);
        ImageView imageView = findViewById(R.id.animalImage);
        Button answer1 = findViewById(R.id.answer_1);
        Button answer2 = findViewById(R.id.answer_2);
        Button answer3 = findViewById(R.id.answer_3);

        resetAnswerButtons(answer1, answer2, answer3);

        correctAnimal = randomizer.generateNewAnimal();
        List<Animal> answerOptionsList = randomizer.makeAnswerOptions();
        setImage(imageView, correctAnimal);

        setAnswerOptionText(answer1, answerOptionsList.get(0));
        setAnswerOptionText(answer2, answerOptionsList.get(1));
        setAnswerOptionText(answer3, answerOptionsList.get(2));

        onClickAnswer(view, answer1, answer2, answer3, correctAnimal, score, model);
    }

    private void resetAnswerButtons(Button answer1, Button answer2, Button answer3) {
        answer1.setBackgroundColor(getResources().getColor(R.color.white));
        answer2.setBackgroundColor(getResources().getColor(R.color.white));
        answer3.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void setImage(ImageView imageView, Animal animal) {
        imageView.setImageBitmap(Converter.convertByteArrayToImage(animal.getImage()));
    }

    private void setAnswerOptionText(Button button, Animal animal) {
        button.setText(animal.getName());
    }

   public void onClickAnswer(View v, Button answer1, Button answer2, Button answer3, Animal a, Score score, QuizScoreModel model) {
       View.OnClickListener onClickListener = view -> {
           Button button = (Button) view;
           if (updateScore(a, button, score, model)) {
               resultView.setText("Correct!");
           } else {
               resultView.setText("Sry, wrong :(");
           }
       };

       answer1.setOnClickListener(onClickListener);
       answer2.setOnClickListener(onClickListener);
       answer3.setOnClickListener(onClickListener);
   }
    private boolean updateScore(Animal a, TextView answer, Score score, QuizScoreModel model) {
        boolean correct = a.getName().equalsIgnoreCase(answer.getText().toString());

        if (correct) {
            score.incrementScore();
        }
        score.incrementAttempts();
        model.setScore(score.getScore(), score.getAttempts());

        return correct;
    }

 /*   private void showFinalScore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("activities.Quiz Completed");
        builder.setMessage("Your final score is: " + score + "/" + quizList.length);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reset the score and current quiz index for the next quiz
                score = 0;
                currentQuiz = 0;
                // Start the quiz again
                displayQuiz(currentQuiz);
            }
        });
        builder.show();
    }

    // Call this method at the end of the quiz, after the user has answered the last question
    private void displayFinalScore() {
        showFinalScore();
    }

*/
}
