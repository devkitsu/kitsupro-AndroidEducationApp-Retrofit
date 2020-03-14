package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_kuis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_kuisgambar extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/sejarah/tebak_gambar/";
    Context mContext;
    ProgressDialog loading;
    String selectRb;
    private static final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    @BindView(R.id.imageView2) ImageView imgSet;
    @BindView(R.id.textView8)  EditText caption;
    @OnClick(R.id.imageView2) void gambar(){
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("image/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, IMG_REQUEST);
    }
    @OnClick(R.id.brgsimpan) void simpan(){
        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage("Loading ...");
        loading.show();
        String gambar = imageToString();
        String nama = caption.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api_kuis api = retrofit.create(api_kuis.class);
        Call<Value> call = api.gambar(gambar,nama);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                loading.dismiss();
                if (value.equals("1")) {
                    notif();
                } else {
                    Toast.makeText(tambah_kuisgambar.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(tambah_kuisgambar.this, "Tambah Data Gagal!", Toast.LENGTH_SHORT).show();
                Log.d("Materi Error", String.valueOf(t));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kuisgambar);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Tambah Data");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null)
        {
            Uri path = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imgSet.setImageBitmap(bitmap);
                imgSet.setVisibility(View.VISIBLE);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //gambar di kompres menjadi JPEG dan kualitasnya 100
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void notif(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Berhasil Tambah Gambar");
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
