package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_kategori;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_kategori extends AppCompatActivity {
    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/kategori/";
    Context mContext;
    ProgressDialog loading;
    String selectRb;
    @BindView(R.id.rdgrp) RadioGroup rb;
    @BindView(R.id.radioBelanda) RadioButton rbBelanda;
    @BindView(R.id.radioJepang) RadioButton rbJepang;
    @BindView(R.id.radioReformasi) RadioButton rbReformasi;
    @OnClick(R.id.brgsimpan) void insert() {
        int rbId = rb.getCheckedRadioButtonId();
        if (rbId == rbBelanda.getId()) {
            selectRb = rbBelanda.getText().toString();
        } else if (rbId == rbJepang.getId()) {
            selectRb = rbJepang.getText().toString();
        } else if (rbId == rbReformasi.getId()) {
            selectRb = rbReformasi.getText().toString();
        } else {
            Toast.makeText(tambah_kategori.this, "Kategori Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String nm_kategori = selectRb;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kategori api = retrofit.create(api_kategori.class);
        Call<Value> call = api.kategori(nm_kategori);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notif();
                } else {
                    Toast.makeText(tambah_kategori.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(tambah_kategori.this, "Tanbah Data Gagal!", Toast.LENGTH_SHORT).show();
                Log.d("Kategori Error", String.valueOf(t));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kategori);
        ButterKnife.bind(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");
    }

    private void notif(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Berhasil Tambah Data Kategori");
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
