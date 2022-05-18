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

import java.util.ArrayList;
import java.util.Locale;

import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainDetailTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;

public class CustomApdapterTB extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThietBiModel> data;
    ArrayList<ThietBiModel> data_DS;

    public CustomApdapterTB(Context context, int resource, ArrayList<ThietBiModel> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<ThietBiModel>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        ImageView imgHinh;
        ImageView imgDetail;
        TextView txtMaTBi;
        TextView txtTenTBi;
        TextView txtXuatXu;
        TextView txtMaLoai;
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
            holder.txtMaTBi = view.findViewById(R.id.txtMaTBi);
            holder.txtTenTBi = view.findViewById(R.id.txtTenTbi);
            holder.txtXuatXu = view.findViewById(R.id.txtXuatXu);
            holder.txtMaLoai = view.findViewById(R.id.txtMaLoai);
            view.setTag(holder);
        }
        else
            holder=(Holder)view.getTag();
        final ThietBiModel thietbi = data.get(position);
        if(thietbi.getMALOAI().equals("DH"))
            holder.imgHinh.setImageResource(R.drawable.dh);
        if((thietbi.getMALOAI().equals("MT")))
            holder.imgHinh.setImageResource(R.drawable.mtinh);
        if((thietbi.getMALOAI().equals("CS")))
            holder.imgHinh.setImageResource(R.drawable.mc);
        if((thietbi.getMALOAI().equals("MC")))
            holder.imgHinh.setImageResource(R.drawable.cs);
        holder.txtMaTBi.setText(thietbi.getMATB());
        holder.txtTenTBi.setText(thietbi.getTENTB());
        holder.txtXuatXu.setText(thietbi.getXUATXU());
        holder.txtMaLoai.setText(thietbi.getMALOAI());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailTB.class);
                Bundle bundle = new Bundle();
                bundle.putString("MATB",thietbi.getMATB());
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });


        return view;
        /*View view= LayoutInflater.from(context).inflate(resource,null);
        ImageView imgPhong= view.findViewById(R.id.imgHinh);
        TextView txtMaLoai = view.findViewById(R.id.txtMa);
        TextView txtTenLoai = view.findViewById(R.id.txtTen);
        ThietBi thietBi = data.get(position);
        if(thietBi.getPhong().equals("Phòng kế toán"))
            imgPhong.setImageResource(R.drawable.ic_create_black_24dp);
        if(thietBi.getPhong().equals("Phòng máy tính"))
            imgPhong.setImageResource(R.drawable.ic_important_devices_black_24dp);
        else
        {
            imgPhong.setImageResource(R.drawable.ic_person_black_24dp);
        }
        txtMaLoai.setText(thietBi.getMaLoai());
        txtTenLoai.setText(thietBi.getTenLoai());
        return  view;*/
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length()==0){
            data.addAll(data_DS);
        }
        else {
            for (ThietBiModel model : data_DS){
                if (model.getTENTB().toLowerCase(Locale.getDefault())
                        .contains(charText) ){
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
