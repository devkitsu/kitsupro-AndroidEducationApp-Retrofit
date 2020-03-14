package com.kitsudev.indohistory.indonesiahistory.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;
import com.kitsudev.indohistory.indonesiahistory.crud.update_kuis;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter_Kuis extends RecyclerView.Adapter<RecyclerViewAdapter_Kuis.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter_Kuis(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__kuis, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_Kuis.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Kuis.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtkode_barang.setText(result.getId_kuis());
        holder.txt_soal.setText(result.getPertanyaan());
        holder.txt_jawaban.setText(result.getJawaban());
        String lvl = result.getLevel();
        if(lvl.equals("1")){
            holder.txt_level.setText("Mudah");
        } else if(lvl.equals("2")){
            holder.txt_level.setText("Normal");
        } else{
            holder.txt_level.setText("Sulit");
        }
        holder.txt_A.setText(result.getJawaban1());
        holder.txt_B.setText(result.getJawaban2());
        holder.txt_C.setText(result.getJawaban3());
        holder.txt_D.setText(result.getJawaban4());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txtkode_barang) TextView txtkode_barang;
        @BindView(R.id.txtsoal) TextView txt_soal;
        @BindView(R.id.txtjawaban) TextView txt_jawaban;
        @BindView(R.id.txtlevel) TextView txt_level;
        @BindView(R.id.txtA) TextView txt_A;
        @BindView(R.id.txtB) TextView txt_B;
        @BindView(R.id.txtC) TextView txt_C;
        @BindView(R.id.txtD) TextView txt_D;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String id = txtkode_barang.getText().toString();
            String soal = txt_soal.getText().toString();
            String jawaban = txt_jawaban.getText().toString();
            String level = txt_level.getText().toString();
            String optA = txt_A.getText().toString();
            String optB = txt_B.getText().toString();
            String optC = txt_C.getText().toString();
            String optD = txt_D.getText().toString();

            Intent i = new Intent(context, update_kuis.class);
            i.putExtra("id_kuis", id);
            i.putExtra("pertanyaan", soal);
            i.putExtra("jawaban", jawaban);
            i.putExtra("level", level);
            i.putExtra("jawaban1", optA);
            i.putExtra("jawaban2", optB);
            i.putExtra("jawaban3", optC);
            i.putExtra("jawaban4", optD);
            context.startActivity(i);
        }
    }
}
