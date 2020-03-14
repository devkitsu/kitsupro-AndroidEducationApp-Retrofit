package com.kitsudev.indohistory.indonesiahistory.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;
import com.kitsudev.indohistory.indonesiahistory.crud.update_gambar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kitsudev.indohistory.indonesiahistory.crud.lihat_kuisgambar.URL;

public class RecyclerViewAdapter_Gambar extends RecyclerView.Adapter<RecyclerViewAdapter_Gambar.ViewHolder> {
    private Context context;
    private List<Result> results;


    public RecyclerViewAdapter_Gambar(Context context, List<Result> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__gambar, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_Gambar.ViewHolder(v);

        return holder;
    }

    public void onBindViewHolder(RecyclerViewAdapter_Gambar.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtkode_barang.setText(result.getId_tebakgambar());
        holder.namaImg.setText(result.getNama());
        String url = URL+"img/"+result.getGambar();
        Picasso.get().load(url).error(R.drawable.ic_menu_camera).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.txtkode_barang) TextView txtkode_barang;
        @BindView(R.id.txtnama) TextView namaImg;
        @BindView(R.id.img) ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            String id = txtkode_barang.getText().toString();
            String nama = namaImg.getText().toString();

            Intent i = new Intent(context, update_gambar.class);
            i.putExtra("id_tebakgambar", id);
            i.putExtra("nama", nama);
            context.startActivity(i);
        }
    }
}
