package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kitsudev.indohistory.indonesiahistory.crud.lihat_kategori;
import com.kitsudev.indohistory.indonesiahistory.crud.tambah_kategori;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class kelola_kategori extends Fragment {
    @OnClick(R.id.bt_createdata) void tambah(){
        Intent intent = new Intent(getActivity(), tambah_kategori.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_viewdata) void lihat(){
        Intent intent = new Intent(getActivity(), lihat_kategori.class);
        startActivity(intent);
    }

    public kelola_kategori(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_kelola_kategori, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Data Kategori");
        return view;
    }
}
