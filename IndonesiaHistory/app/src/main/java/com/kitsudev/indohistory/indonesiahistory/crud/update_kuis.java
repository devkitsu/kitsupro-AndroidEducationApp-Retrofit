package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class update_kuis extends AppCompatActivity {
    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/kuis/";
    Context mContext;
    ProgressDialog loading;
    String mLvl,selectLvl;
    @BindView(R.id.et_idbrg) TextView idkuis;
    @BindView(R.id.rdgrp2) RadioGroup rb2;
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
        int rbId2 = rb2.getCheckedRadioButtonId();
        if (rbId2 == rbMudah.getId()) {
            selectLvl = "1";
        } else if (rbId2 == rbNormal.getId()) {
            selectLvl = "2";
        } else if (rbId2 == rbSulit.getId()) {
            selectLvl = "3";
        } else {
            Toast.makeText(update_kuis.this, "Level Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }
        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String id_kuis = idkuis.getText().toString();
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
        Call<Value> call = api.ubah(id_kuis,soal,jawaban,optA,optB,optC,optD,level);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notifUbah();
                } else {
                    Toast.makeText(update_kuis.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(update_kuis.this, "Tambah Data Gagal!", Toast.LENGTH_SHORT).show();
                Log.d("Materi Error", String.valueOf(t));
            }
        });
    }
    @OnClick(R.id.hapus) void hapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Apakah anda yakin ingin hapus?");
        alertDialogBuilder.setIcon(R.drawable.warn).setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //mengambil data dari edittext
                        String id_kuis = idkuis.getText().toString();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        api_kuis api = retrofit.create(api_kuis.class);
                        Call<Value> call = api.hapus(id_kuis);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")) {
                                    notifHapus();
                                } else {
                                    Toast.makeText(update_kuis.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(update_kuis.this, "Hapus Data Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kuis);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        String soal = intent.getStringExtra("pertanyaan");
        String jawaban = intent.getStringExtra("jawaban");
        String id = intent.getStringExtra("id_kuis");
        String optA = intent.getStringExtra("jawaban1");
        String optB = intent.getStringExtra("jawaban2");
        String optC = intent.getStringExtra("jawaban3");
        String optD = intent.getStringExtra("jawaban4");

        idkuis.setText(id);
        pertanyaan.setText(soal);
        jwb.setText(jawaban);
        pil_A.setText(optA);
        pil_B.setText(optB);
        pil_C.setText(optC);
        pil_D.setText(optD);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kelola Data Kuis");
    }
    private void notifUbah(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Data Kuis berhasil Diubah!");
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
    private void notifHapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Berhasil Hapus Data Kuis");
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
