package com.kitsudev.indohistoryclient;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class kuis extends AppCompatActivity {
    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/kuis/";
    private List<Result> results = new ArrayList<>();
    private String mAnswer, mChoice1, mChoice2, mChoice3, mChoice4, numQ, nama, levelQ;
    private Button mButtonChoice1,  mButtonChoice2, mButtonChoice3, mButtonChoice4, mExit;
    private int mScore = 0, qNum = 0, listQ, benar=0, salah=0;
    @BindView(R.id.score) TextView skor;
    @BindView(R.id.question) TextView pertanyaan;
    @BindView(R.id.level) TextView mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Selamat Bermain!");
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        levelQ = intent.getStringExtra("level");
        if(levelQ.equals("1")){
            mLevel.setText("Mudah");
        } else if(levelQ.equals("2")){
            mLevel.setText("Normal");
        } else{
            mLevel.setText("Sulit");
        }
        mButtonChoice1= (Button) findViewById(R.id.choice1);
        mButtonChoice2= (Button) findViewById(R.id.choice2);
        mButtonChoice3= (Button) findViewById(R.id.choice3);
        mButtonChoice4= (Button) findViewById(R.id.choice4);
        mExit= (Button) findViewById(R.id.quit);
        updateQuestion();
        //Pilihan A
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    benar=benar+1;
                    updateScore(mScore);
                    notifBenar();
                    qNum++;
                    updateQuestion();
                }
                else{
                    notifSalah();
                    salah=salah+1;
                    qNum++;
                    updateQuestion();
                }
            }
        });
        //Pilihan B
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    benar=benar+1;
                    updateScore(mScore);
                    notifBenar();
                    qNum++;
                    updateQuestion();
                }
                else{
                    notifSalah();
                    salah=salah+1;
                    qNum++;
                    updateQuestion();
                }
            }
        });
        //Pilihan C
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    benar=benar+1;
                    updateScore(mScore);
                    notifBenar();
                    qNum++;
                    updateQuestion();
                }
                else{
                    notifSalah();
                    salah=salah+1;
                    qNum++;
                    updateQuestion();
                }
            }
        });
        //Pilihan D
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonChoice4.getText().equals(mAnswer)){
                    mScore=mScore+10;
                    benar=benar+1;
                    updateScore(mScore);
                    notifBenar();
                    qNum++;
                    updateQuestion();
                }
                else{
                    notifSalah();
                    salah=salah+1;
                    qNum++;
                    updateQuestion();
                }
            }
        });
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Result();
            }
        });
    }

    public void Result(){
        String score = skor.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.skor(nama,score,levelQ);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    Intent intent = new Intent(kuis.this, hasil_kuis.class);
                    Bundle b = new Bundle();
                    b.putInt("score",mScore);
                    b.putInt("benar",benar);
                    b.putInt("salah",salah);
                    b.putString("nama",nama);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }

    private void updateScore(int score){
        skor.setText("" + mScore);
        Log.d("Score", "Your score: "+score);
    }

    public void updateQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getsoal(levelQ);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    showQ();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }

    public void showQ(){
        listQ = results.size();
        if(qNum < listQ) {
            numQ = results.get(qNum).getPertanyaan();
            mAnswer = results.get(qNum).getJawaban();
            mChoice1 = results.get(qNum).getJawaban1();
            mChoice2 = results.get(qNum).getJawaban2();
            mChoice3 = results.get(qNum).getJawaban3();
            mChoice4 = results.get(qNum).getJawaban4();
            pertanyaan.setText(numQ);
            mButtonChoice1.setText(mChoice1);
            mButtonChoice2.setText(mChoice2);
            mButtonChoice3.setText(mChoice3);
            mButtonChoice4.setText(mChoice4);
        } else {
            Result();
            finish();
        }
    }

    private void notifBenar(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Hore! Kamu Pintar, Jawaban Kamu Benar ^-^");
        alertDialogBuilder.setIcon(R.drawable.confirm).setCancelable(true);
        alertDialogBuilder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void notifSalah(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Yah Jawaban Kamu Salah.. Belajar Lagi Yaa! ^-^");
        alertDialogBuilder.setIcon(R.drawable.warn).setCancelable(true);
        alertDialogBuilder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
