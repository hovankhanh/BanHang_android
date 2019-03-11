package com.example.khanhho.drawer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanhho.drawer.Model.Sanpham;
import com.example.khanhho.drawer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ViewHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View sanphamview = inflater.inflate(R.layout.dong_sanphammoinhat, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(sanphamview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Sanpham sanpham = arraysanpham.get(i);
        viewHolder.txvtensanpham.setText(sanpham.getTensanpham());
        viewHolder.txtgiasanpham.setText(String.valueOf(sanpham.getGiasanpham()));
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.slide1).error(R.drawable.slide2).into(viewHolder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imghinhsanpham;
        public TextView txvtensanpham, txtgiasanpham;

        public ViewHolder(View itemView) {
            super(itemView);
            imghinhsanpham = (ImageView) itemView.findViewById(R.id.imagesanpham);
            txvtensanpham = (TextView) itemView.findViewById(R.id.tvtensanpham);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.tvtgiasanpham);
        }
    }

}
