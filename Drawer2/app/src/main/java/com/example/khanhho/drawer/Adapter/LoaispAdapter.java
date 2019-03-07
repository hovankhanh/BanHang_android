package com.example.khanhho.drawer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanhho.drawer.Model.Loaisp;
import com.example.khanhho.drawer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txttentloaisp;
        ImageView imgloaisp;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttentloaisp = (TextView) convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView) convertView.findViewById(R.id.imageviewloaisp);
            convertView.setTag(viewHolder);

        }else {
            viewHolder  = (ViewHolder)convertView.getTag();
        }
        Loaisp loaisp = arrayListloaisp.get(position);
        viewHolder.txttentloaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp()).placeholder(R.drawable.slide1).error(R.drawable.slide2).into(viewHolder.imgloaisp);
        return convertView;
    }
}
