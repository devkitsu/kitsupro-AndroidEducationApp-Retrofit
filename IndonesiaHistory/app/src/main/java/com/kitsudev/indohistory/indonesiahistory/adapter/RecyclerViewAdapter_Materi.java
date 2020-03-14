package com.kitsudev.indohistory.indonesiahistory.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;
import com.kitsudev.indohistory.indonesiahistory.crud.update_kategori;
import com.kitsudev.indohistory.indonesiahistory.crud.update_materi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter_Materi extends RecyclerView.Adapter<RecyclerViewAdapter_Materi.ViewHolder> {
    private Context context;
    private List<Result> results;


    public RecyclerViewAdapter_Materi(Context context, List<Result> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__materi, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_Materi.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Materi.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtkode_barang.setText(result.getId_materi());
        holder.txtnama_barang.setText(result.getNama_materi());
        holder.txt_isi.setText(result.getIsi());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txtkode_barang) TextView txtkode_barang;
        @BindView(R.id.txtnama_barang) TextView txtnama_barang;
        @BindView(R.id.txtisi) TextView txt_isi;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String id = txtkode_barang.getText().toString();
            String nama = txtnama_barang.getText().toString();
            String isi = txt_isi.getText().toString();

            Intent i = new Intent(context, update_materi.class);
            i.putExtra("id_materi", id);
            i.putExtra("nm_materi", nama);
            i.putExtra("isi", isi);
            context.startActivity(i);
        }
    }
}
