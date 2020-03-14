package com.kitsudev.indohistory.indonesiahistory.laporan;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitsudev.indohistory.indonesiahistory.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class laporan extends Fragment {
    @OnClick(R.id.bt_createdata) void skor(){
        Intent intent = new Intent(getActivity(), laporan_skor.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_viewdata) void user(){
        Intent intent = new Intent(getActivity(), laporan_user.class);
        startActivity(intent);
    }

    public laporan(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_laporan, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Data Kategori");
        return view;
    }
}
