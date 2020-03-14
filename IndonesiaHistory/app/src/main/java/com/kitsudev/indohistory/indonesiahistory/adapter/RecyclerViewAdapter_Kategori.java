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
import com.kitsudev.indohistory.indonesiahistory.crud.update_kategori;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter_Kategori extends RecyclerView.Adapter<RecyclerViewAdapter_Kategori.ViewHolder> {
    private Context context;
    private List<Result> results;


    public RecyclerViewAdapter_Kategori(Context context, List<Result> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__kategori, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_Kategori.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Kategori.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtkode_barang.setText(result.getId_kategori());
        holder.txtnama_barang.setText(result.getNama_kategori());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txtkode_barang) TextView txtkode_barang;
        @BindView(R.id.txtnama_barang) TextView txtnama_barang;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String id = txtkode_barang.getText().toString();
            String nama = txtnama_barang.getText().toString();

            Intent i = new Intent(context, update_kategori.class);
            i.putExtra("id_kategori", id);
            i.putExtra("nm_kategori", nama);
            context.startActivity(i);
        }
    }
}
