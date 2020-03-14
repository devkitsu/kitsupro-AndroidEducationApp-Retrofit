package com.kitsudev.indohistory.indonesiahistory.crud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.api.api_kuis;

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

public class update_gambar extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/sejarah/tebak_gambar/";
    private List<Result> results = new ArrayList<>();
    Context mContext;
    ProgressDialog loading;
    Toolbar toolbar;
    @BindView(R.id.et_idbrg)
    TextView et_idbrg;
    @BindView(R.id.textnama_barang) TextView textnama_barang;
    @OnClick(R.id.buttonDelPel) void hapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Apakah anda yakin ingin hapus?");
        alertDialogBuilder.setIcon(R.drawable.warn).setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //mengambil data dari edittext
                        String id_tebakgambar = et_idbrg.getText().toString();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        api_kuis api = retrofit.create(api_kuis.class);
                        Call<Value> call = api.hapusgambar(id_tebakgambar);
                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                String value = response.body().getValue();
                                String message = response.body().getMessage();
                                if (value.equals("1")) {
                                    notif();
                                } else {
                                    Toast.makeText(update_gambar.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(update_gambar.this, "Hapus Data Error!", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_update_gambar);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String id = intent.getStringExtra("id_tebakgambar");

        et_idbrg.setText(id);
        textnama_barang.setText(nama);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Hapus Data");

    }

    private void notif(){
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
}
