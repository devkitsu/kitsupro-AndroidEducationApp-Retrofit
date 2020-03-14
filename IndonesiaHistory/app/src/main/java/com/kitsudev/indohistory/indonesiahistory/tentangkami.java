package com.kitsudev.indohistory.indonesiahistory;

import android.app.Fragment;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class tentangkami extends Fragment {

    public tentangkami(){}
    ConstraintLayout view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (ConstraintLayout) inflater.inflate(R.layout.tentangkami, container, false);

        getActivity().setTitle("Tentang Kami");

        return view;
    }
}