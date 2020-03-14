package com.kitsudev.indohistoryclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    String nama;
    @OnClick(R.id.button) void belajar(){
        Intent intent = new Intent(MainActivity.this, materi.class);
        startActivity(intent);
    }
    @OnClick(R.id.button2) void kuis(){
        Intent intent = new Intent(MainActivity.this, pilih_level.class);
        intent.putExtra("nama", nama);
        startActivity(intent);
    }
    @OnClick(R.id.button4) void skor(){
        Intent intent = new Intent(MainActivity.this, skor_kuis.class);
        startActivity(intent);
    }
    @OnClick(R.id.button5) void exit(){
        finish();
    }
    @BindView(R.id.textView)  TextView txtnama;
    @BindView(R.id.textView4) TextView txtuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        txtnama.setText(nama);
    }
}
