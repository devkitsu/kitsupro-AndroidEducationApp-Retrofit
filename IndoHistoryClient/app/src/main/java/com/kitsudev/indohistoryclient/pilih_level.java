package com.kitsudev.indohistoryclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pilih_level extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/aldo/kuis/";
    private List<Result> results = new ArrayList<>();
    String nama, level, levelQ;
    @BindView(R.id.jmlSoal) TextView soal3;
    @BindView(R.id.jmlSoal2) TextView soal;
    @BindView(R.id.jmlSoal3) TextView soal2;
    @BindView(R.id.jmlSkor) TextView skor3;
    @BindView(R.id.jmlSkor2) TextView skor;
    @BindView(R.id.jmlSkor3) TextView skor2;
    @OnClick(R.id.button3) void mudah(){
        level="1";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @OnClick(R.id.button4) void normal(){
        level="2";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @OnClick(R.id.button5) void sulit(){
        level="3";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @OnClick(R.id.button6) void mudah2(){
        level="1";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @OnClick(R.id.button7) void normal2(){
        level="2";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @OnClick(R.id.button8) void sulit2(){
        level="3";
        Intent intent = new Intent(pilih_level.this, kuis.class);
        intent.putExtra("nama", nama);
        intent.putExtra("level", level);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pilih_level);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        loadSkorMudah();
        loadSkorSedang();
        loadSkorSulit();
        loadSoalMudah();
        loadSoalSedang();
        loadSoalSulit();
    }

    private void loadSoalMudah(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getsoalmudah();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    soal.setText(results.get(0).getSoalMudah());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
    private void loadSoalSedang(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getsoalsedang();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    soal2.setText(results.get(0).getSoalSedang());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
    private void loadSoalSulit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getsoalsulit();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    soal3.setText(results.get(0).getSoalSulit());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
    private void loadSkorMudah(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getskormudah(nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    skor.setText(results.get(0).getSkorMudah());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
    private void loadSkorSedang(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getskorsedang(nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    skor2.setText(results.get(0).getSkorSedang());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }
    private void loadSkorSulit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apikuis api = retrofit.create(apikuis.class);
        Call<Value> call = api.getskorsulit(nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    results = response.body().getResult();
                    skor3.setText(results.get(0).getSkorSulit());
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Log.d("Materi Error", "onFailure: ERROR > "+ t.toString());
            }
        });
    }

}
