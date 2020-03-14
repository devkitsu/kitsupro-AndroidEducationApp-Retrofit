package com.kitsudev.indohistoryclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kitsudev.indohistoryclient.materi.URL;

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
        holder.txt_gambar.setText(result.getGambar());
        holder.txtkode_barang.setText(result.getId_materi());
        holder.txttgl.setText(result.getTanggal());
        holder.txtnama_barang.setText(result.getNama_materi());
        holder.txt_isi.setText(result.getIsi());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txtkode_barang) TextView txtkode_barang;
        @BindView(R.id.tgl) TextView txttgl;
        @BindView(R.id.txtnama_barang) TextView txtnama_barang;
        @BindView(R.id.txtisi) TextView txt_isi;
        @BindView(R.id.gambar) TextView txt_gambar;

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
            String gambar = txt_gambar.getText().toString();
            String tanggal = txttgl.getText().toString();

            Intent i = new Intent(context, baca_materi.class);
            i.putExtra("id_materi", id);
            i.putExtra("nm_materi", nama);
            i.putExtra("isi", isi);
            i.putExtra("gambar", gambar);
            i.putExtra("tanggal", tanggal);
            context.startActivity(i);
        }
    }
}
