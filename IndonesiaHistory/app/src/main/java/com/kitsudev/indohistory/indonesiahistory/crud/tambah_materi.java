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
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_materi extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/aldo/materi/";
    Context mContext;
    ProgressDialog loading;
    String selectRb;
    private static final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    @BindView(R.id.rdgrp) RadioGroup rb;
    @BindView(R.id.radioBelanda) RadioButton rbBelanda;
    @BindView(R.id.radioJepang) RadioButton rbJepang;
    @BindView(R.id.radioReformasi) RadioButton rbReformasi;
    @BindView(R.id.judul) EditText txt_judul;
    @BindView(R.id.isi) EditText txt_isi;
    @BindView(R.id.img_thumb) ImageView img;
    @OnClick(R.id.img_thumb) void btnImg(){
        pilihImg();
    }

    @OnClick(R.id.brgsimpan) void simpan(){
        int rbId = rb.getCheckedRadioButtonId();
        if (rbId == rbBelanda.getId()) {
            selectRb = "1";
        } else if (rbId == rbJepang.getId()) {
            selectRb = "2";
        } else if (rbId == rbReformasi.getId()) {
            selectRb = "3";
        } else {
            Toast.makeText(tambah_materi.this, "Kategori Belum Dipilih!", Toast.LENGTH_SHORT).show();
        }

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String id_kategori = selectRb;
        String nm_materi = txt_judul.getText().toString();
        String isi = txt_isi.getText().toString();
        String gambar = imageToString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_materi api = retrofit.create(api_materi.class);
        Call<Value> call = api.materi(nm_materi,id_kategori,isi,gambar);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notif();
                } else {
                    Toast.makeText(tambah_materi.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(tambah_materi.this, "Tambah Data Gagal!", Toast.LENGTH_SHORT).show();
                Log.d("Materi Error", String.valueOf(t));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_materi);
        ButterKnife.bind(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();

            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void pilihImg(){
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, IMG_REQUEST);
    }

    //method ini untuk di saring dari base64 ke String (disini memakai base64 karena kalau 2gb> pasti ngelag uploadnya
    private String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //gambar di kompres menjadi JPEG dan kualitasnya 100
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void notif(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Berhasil Tambah Data Materi");
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
