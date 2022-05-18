package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.model.LoaiThietBiModel;

import java.util.ArrayList;
import java.util.Locale;

import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainDetailLTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;

public class CustomApdapterLTB extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<LoaiThietBiModel> data;
    ArrayList<LoaiThietBiModel> data_DS;

    public CustomApdapterLTB(Context context, int resource, ArrayList<LoaiThietBiModel> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<LoaiThietBiModel>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView tvHoTen;
        TextView tvMa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =convertView;
        Holder holder = null;
        if(view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.imgHinh = view.findViewById(R.id.imgHinh);
            holder.imgDetail = view.findViewById(R.id.imgDetail);
            holder.tvHoTen = view.findViewById(R.id.tvTen);
            holder.tvMa = view.findViewById(R.id.tvMa);
            view.setTag(holder);
        }
        else
            holder=(Holder)view.getTag();
        final LoaiThietBiModel loaithietBi = data.get(position);
        holder.tvHoTen.setText(loaithietBi.getTENLOAI());
        holder.tvMa.setText(loaithietBi.getMALOAI());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailLTB.class);
                Bundle bundle = new Bundle();
                bundle.putString("MALOAI",loaithietBi.getMALOAI());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length()==0){
            data.addAll(data_DS);
        }
        else {
            for (LoaiThietBiModel model : data_DS){
                if (model.getTENLOAI().toLowerCase(Locale.getDefault())
                        .contains(charText) ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
