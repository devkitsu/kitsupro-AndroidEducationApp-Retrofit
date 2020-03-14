package com.kitsudev.indohistory.indonesiahistory.test;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;


public class KuisMulai extends AppCompatActivity {

    private TextView mScoreView, mQuestionView;
    private Button mButtonChoice1,  mButtonChoice2, mButtonChoice3, mButtonChoice4, mExit;
    private String mAnswer;
    private int mScore = 0;
    private int qNum = 0;
    private int Num = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis_mulai);
        mScoreView= (TextView) findViewById(R.id.score);
        mQuestionView= (TextView) findViewById(R.id.question);
        mButtonChoice1= (Button) findViewById(R.id.choice1);
        mButtonChoice2= (Button) findViewById(R.id.choice2);
        mButtonChoice3= (Button) findViewById(R.id.choice3);
        mButtonChoice4= (Button) findViewById(R.id.choice4);
        mExit= (Button) findViewById(R.id.quit);
        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    updateScore(mScore);
                    Toast.makeText(KuisMulai.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    Toast.makeText(KuisMulai.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    updateScore(mScore);
                    Toast.makeText(KuisMulai.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    Toast.makeText(KuisMulai.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    updateScore(mScore);
                    Toast.makeText(KuisMulai.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    Toast.makeText(KuisMulai.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice4.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    updateScore(mScore);
                    Toast.makeText(KuisMulai.this, "correct", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
                else{
                    Toast.makeText(KuisMulai.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result();
            }
        });

    }
    public void Result(){

        Intent intent = new Intent(KuisMulai.this, KuisHasil.class);
        Bundle b = new Bundle();
        b.putInt("score",mScore);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    private void updateScore(int score){
        mScoreView.setText("" + mScore);
        Log.d("Score", "Your score: "+score);
    }
    public void updateQuestion(){

        qNum++;
        Num++;
            }
}
