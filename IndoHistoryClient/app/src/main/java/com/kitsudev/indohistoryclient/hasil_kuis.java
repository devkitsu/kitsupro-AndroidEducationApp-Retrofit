package com.kitsudev.indohistoryclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class hasil_kuis extends AppCompatActivity {
    @BindView(R.id.skor) TextView mSkor;
    @BindView(R.id.benar) TextView mBenar;
    @BindView(R.id.salah) TextView mSalah;
    @BindView(R.id.nama) TextView mNama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
        int skor = b.getInt("score");
        int benar = b.getInt("benar");
        int salah = b.getInt("salah");
        String nama = b.getString("nama");
        mSkor.setText(String.valueOf(skor));
        mBenar.setText(String.valueOf(benar));
        mSalah.setText(String.valueOf(salah));
        mNama.setText(nama);
    }
}
