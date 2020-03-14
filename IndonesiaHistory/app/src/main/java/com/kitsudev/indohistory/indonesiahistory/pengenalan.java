package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;

public class pengenalan extends Fragment {
    public pengenalan(){}
    ConstraintLayout view;
    @BindView(R.id.nama) TextView Nama;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (ConstraintLayout) inflater.inflate(R.layout.pengenalan, container, false);
        Bundle args = getArguments();
       // String nama = args.getString("result_nama");
       // String uid = args.getString("uid");
        getActivity().setTitle("Administrator");
       // Uid.setText(uid);
       // Nama.setText(nama);
        return view;
    }

}
