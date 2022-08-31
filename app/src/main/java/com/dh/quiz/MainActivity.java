package com.dh.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";


    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_americas, true),
            new Question(R.string.question_china, true),
            new Question(R.string.question_australia, false)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");
        if (savedInstanceState != null){
            // recovery index from state when system called onCreate function
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text);
        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        updateQuestion();

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next = mCurrentIndex - 1;
                if (next < 0){
                    next = mQuestionBank.length -1;
                }
                mCurrentIndex = next % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        boolean hasAnswered =  mQuestionBank[mCurrentIndex].hasAnswered();
        if (hasAnswered){
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        boolean anwserIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId;
        if (userPressedTrue == anwserIsTrue){
            messageResId = R.string.correct;
        }else {
            messageResId = R.string.incorrect;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        mQuestionBank[mCurrentIndex].setAnswered(true);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSave Instance State");
        //save index to state to avoid lost index
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
    }
    
}