package com.kitsudev.indohistoryclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class baca_materi extends AppCompatActivity {
    public static final String URL = "http://192.168.43.66/aldo/materi/";
    private List<Result> results = new ArrayList<>();
    Context mContext;
    ProgressDialog loading;
    @BindView(R.id.imageView) ImageView img;
    @BindView(R.id.judul) TextView judul_materi;
    @BindView(R.id.tgl) TextView tanggal;
    @BindView(R.id.isi) TextView isi_materi;
    @BindView(R.id.id) TextView id_materi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baca_materi);
        ButterKnife.bind(this);
        mContext = this;
        Intent intent = getIntent();
        String id = intent.getStringExtra("id_materi");
        String judul = intent.getStringExtra("nm_materi");
        String isi = intent.getStringExtra("isi");
        String gambar = intent.getStringExtra("gambar");
        String tgl = intent.getStringExtra("tanggal");
        String url = URL+"img/"+gambar;
        Picasso.get().load(url).error(R.drawable.ic_menu_camera).into(img);
        id_materi.setText(id);
        judul_materi.setText(judul);
        tanggal.setText(tgl);
        isi_materi.setText(isi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Baca Materi");
    }
}
