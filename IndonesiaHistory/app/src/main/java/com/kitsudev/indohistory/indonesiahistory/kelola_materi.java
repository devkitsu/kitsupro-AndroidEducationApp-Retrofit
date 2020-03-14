package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kitsudev.indohistory.indonesiahistory.crud.lihat_materi;
import com.kitsudev.indohistory.indonesiahistory.crud.tambah_materi;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class kelola_materi extends Fragment {
    public kelola_materi(){}
    @OnClick(R.id.bt_createdata) void tambah(){
        Intent intent = new Intent(getActivity(), tambah_materi.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_viewdata) void lihat(){
        Intent intent = new Intent(getActivity(), lihat_materi.class);
        startActivity(intent);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_kelola_materi, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Data Materi");
        return view;

    }

}
