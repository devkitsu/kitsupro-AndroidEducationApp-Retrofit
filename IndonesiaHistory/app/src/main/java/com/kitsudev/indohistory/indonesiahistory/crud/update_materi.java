package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_materi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

public class update_materi extends AppCompatActivity {
    public static final String URL = "http://kitsudev.000webhostapp.com/aldo/materi/";
    private List<Result> results = new ArrayList<>();
    Context mContext;
    ProgressDialog loading;
    String selectRb;
    @BindView(R.id.txtid) TextView et_id;
    @BindView(R.id.judul) EditText judul;
    @BindView(R.id.isi) EditText txt_isi;
    @BindView(R.id.rdgrp) RadioGroup rb;
    @BindView(R.id.radioBelanda) RadioButton rbBelanda;
    @BindView(R.id.radioJepang) RadioButton rbJepang;
    @BindView(R.id.radioReformasi) RadioButton rbReformasi;
    @OnClick(R.id.brgsimpan) void ubah(){
        int rbId = rb.getCheckedRadioButtonId();
        if (rbId == rbBelanda.getId()) {
            selectRb = "1";
        } else if (rbId == rbJepang.getId()) {
            selectRb = "2";
        } else if (rbId == rbReformasi.getId()) {
            selectRb = "3";
        } else {
            Toast.makeText(update_materi.this, "Kategori Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String id_kategori = selectRb;
        String nm_materi = judul.getText().toString();
        String id_materi = et_id.getText().toString();
        String isi = txt_isi.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_materi api = retrofit.create(api_materi.class);
        Call<Value> call = api.ubah(nm_materi,id_kategori,isi,id_materi);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notifUbah();
                } else {
                    Toast.makeText(update_materi.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(update_materi.this, "Ubah Data Gagal!", Toast.LENGTH_SHORT).show();
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
                        String id_materi = et_id.getText().toString();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        api_materi api = retrofit.create(api_materi.class);
                        Call<Value> call = api.hapus(id_materi);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")) {
                                    notifHapus();
                                } else {
                                    Toast.makeText(update_materi.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(update_materi.this, "Hapus Data Error!", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_update_materi);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nm_materi");
        String id = intent.getStringExtra("id_materi");
        String isi = intent.getStringExtra("isi");

        et_id.setText(id);
        judul.setText(nama);
        txt_isi.setText(isi);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Kelola Data");
    }

    private void notifHapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Data telah dihapus");
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

    private void notifUbah(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Data berhasil diubah");
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
