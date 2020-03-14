package com.kitsudev.indohistory.indonesiahistory.laporan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kitsudev.indohistory.indonesiahistory.R;
import com.kitsudev.indohistory.indonesiahistory.crud.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter_Skor extends RecyclerView.Adapter<RecyclerViewAdapter_Skor.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter_Skor(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__skor, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_Skor.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_Skor.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtNama.setText(result.getNama());
        holder.txtSkor.setText(result.getSkor());
        String levelQ = result.getLevel();
        if(levelQ.equals("1")){
            holder.txtLevel.setText("Mudah");
        } else if(levelQ.equals("2")){
            holder.txtLevel.setText("Normal");
        } else{
            holder.txtLevel.setText("Sulit");
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nama) TextView txtNama;
        @BindView(R.id.skor) TextView txtSkor;
        @BindView(R.id.level) TextView txtLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
