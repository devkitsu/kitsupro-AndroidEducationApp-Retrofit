package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitsudev.indohistory.indonesiahistory.crud.lihat_kuis;
import com.kitsudev.indohistory.indonesiahistory.crud.tambah_kuis;
import com.kitsudev.indohistory.indonesiahistory.test.mulai_kuis;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class kelola_kuis extends Fragment {
    public kelola_kuis(){}
    @OnClick(R.id.bt_createdata) void tambah(){
        Intent intent = new Intent(getActivity(), tambah_kuis.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_viewdata) void lihat() {
        Intent intent = new Intent(getActivity(), lihat_kuis.class);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_kelola_kuis, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Data Kuis");
        return view;

    }

}
