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

public class RecyclerViewAdapter_User extends RecyclerView.Adapter<RecyclerViewAdapter_User.ViewHolder> {
    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter_User(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_adapter__user, parent, false);
        ViewHolder holder = new RecyclerViewAdapter_User.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter_User.ViewHolder holder, final int position) {
        Result result = results.get(position);
        holder.txtNama.setText(result.getNama());
        holder.txtLevel.setText(result.getUid());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nama) TextView txtNama;
        @BindView(R.id.level) TextView txtLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
