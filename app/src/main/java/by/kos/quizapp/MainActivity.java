package by.kos.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonTrue;
    private Button buttonFalse;
    private TextView textViewQuestion;
    private TextView textViewQuizStats;
    private ProgressBar quizProgressBar;
    private int mQuestionIndex = 0;
    private int mQuizQuestion;
    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,true),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,true),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,true),
    };
    private int mUserScore;
    final private int PROGRESS_CONST = 1;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTrue = findViewById(R.id.buttonTrue);
        buttonFalse = findViewById(R.id.buttonFalse);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuizStats = findViewById(R.id.textViewQuizStats);
        quizProgressBar = findViewById(R.id.quizProgressBar);
        textViewQuestion.setText(questionCollection[mQuestionIndex].getQuestion());
        textViewQuizStats.setText(mUserScore+"/"+questionCollection.length);
        toast = new Toast(this);

        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserAnswers(true);
                changeQuestion();
            }
        });


        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserAnswers(false);
                changeQuestion();
            }
        });
    }

    private void changeQuestion(){
        if(mQuestionIndex<questionCollection.length-1) {
            mQuestionIndex++;
            mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
            textViewQuestion.setText(mQuizQuestion);
        } else {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Вопросы кончились.");
            quizAlert.setMessage("Правильных ответов "+mUserScore);
            quizAlert.setPositiveButton("Завершить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    finish();
                }
            });
            quizAlert.setNegativeButton("Ещё раз", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    toast.cancel();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            quizAlert.show();
        }
        quizProgressBar.incrementProgressBy(PROGRESS_CONST);
        textViewQuizStats.setText(mUserScore+"/"+questionCollection.length);

    }

    private void checkUserAnswers(boolean user_answer){
        boolean correctAnswer = questionCollection[mQuestionIndex].isAnswer();
            if (correctAnswer == user_answer) {
                toast.cancel();
                toast = Toast.makeText(getApplicationContext(), R.string.correct_toast_text, Toast.LENGTH_SHORT);
                toast.show();
                mUserScore++;
            } else {
                toast.cancel();
                toast =  Toast.makeText(getApplicationContext(), R.string.incorrect_toast_text, Toast.LENGTH_SHORT);
                toast.show();
            }
    }

}