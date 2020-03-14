package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_kuis;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_kuis extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/aldo/kuis/";
    Context mContext;
    ProgressDialog loading;
    String selectRb, selectLvl;
    @BindView(R.id.rdgrp) RadioGroup rb;
    @BindView(R.id.rdgrp2) RadioGroup rb2;
    @BindView(R.id.radioBelanda) RadioButton rbBelanda;
    @BindView(R.id.radioJepang) RadioButton rbJepang;
    @BindView(R.id.radioReformasi) RadioButton rbReformasi;
    @BindView(R.id.radioMudah) RadioButton rbMudah;
    @BindView(R.id.radioNormal) RadioButton rbNormal;
    @BindView(R.id.radioSulit) RadioButton rbSulit;
    @BindView(R.id.A) EditText pil_A;
    @BindView(R.id.B) EditText pil_B;
    @BindView(R.id.C) EditText pil_C;
    @BindView(R.id.D) EditText pil_D;
    @BindView(R.id.judul) EditText pertanyaan;
    @BindView(R.id.Jawaban) EditText jwb;
    @OnClick(R.id.brgsimpan) void simpan(){
        int rbId = rb.getCheckedRadioButtonId();
        int rbId2 = rb2.getCheckedRadioButtonId();
        if (rbId == rbBelanda.getId()) {
            selectRb = "1";
        } else if (rbId == rbJepang.getId()) {
            selectRb = "2";
        } else if (rbId == rbReformasi.getId()) {
            selectRb = "3";
        } else {
            Toast.makeText(tambah_kuis.this, "Kategori Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }
        if (rbId2 == rbMudah.getId()) {
            selectLvl = "1";
        } else if (rbId2 == rbNormal.getId()) {
            selectLvl = "2";
        } else if (rbId2 == rbSulit.getId()) {
            selectLvl = "3";
        } else {
            Toast.makeText(tambah_kuis.this, "Level Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String id_kategori = selectRb;
        String level = selectLvl;
        String soal = pertanyaan.getText().toString();
        String jawaban = jwb.getText().toString();
        String optA = pil_A.getText().toString();
        String optB = pil_B.getText().toString();
        String optC = pil_C.getText().toString();
        String optD = pil_D.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kuis api = retrofit.create(api_kuis.class);
        Call<Value> call = api.kuis(soal,jawaban,optA,optB,optC,optD,id_kategori,level);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notif();
                } else {
                    Toast.makeText(tambah_kuis.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(tambah_kuis.this, "Tambah Data Gagal!", Toast.LENGTH_SHORT).show();
                Log.d("Materi Error", String.valueOf(t));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kuis);
        ButterKnife.bind(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");
    }

    private void notif(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Berhasil Tambah Data Kuis");
        alertDialogBuilder.setIcon(R.drawable.confirm).setCancelable(true);
        alertDialogBuilder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
