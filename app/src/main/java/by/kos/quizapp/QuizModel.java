package by.kos.quizapp;

public class QuizModel {
    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public boolean isAnswer() {
        return mAnswer;
    }
}
