package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitsudev.indohistory.indonesiahistory.crud.lihat_kuisgambar;
import com.kitsudev.indohistory.indonesiahistory.crud.tambah_kuisgambar;
import com.kitsudev.indohistory.indonesiahistory.test.mulai_kuis;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class kelola_kuisgambar extends Fragment {
    public kelola_kuisgambar(){}
    @OnClick(R.id.bt_createdata) void tambah(){
        Intent intent = new Intent(getActivity(), tambah_kuisgambar.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_viewdata) void lihat() {
        Intent intent = new Intent(getActivity(), lihat_kuisgambar.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_mulai) void mulai() {
        Intent intent = new Intent(getActivity(), mulai_kuis.class);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_kelola_kuisgambar, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Data Tebak Gambar");
        return view;

    }
}
