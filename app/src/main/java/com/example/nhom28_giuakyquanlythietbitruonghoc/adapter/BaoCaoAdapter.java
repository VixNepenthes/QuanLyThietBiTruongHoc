package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.BaoCaoModel;

import java.util.ArrayList;

public class BaoCaoAdapter extends ArrayAdapter<BaoCaoModel> {
    Context context;
    int resource;
    ArrayList<BaoCaoModel> data;

    public BaoCaoAdapter(@NonNull Context context, int resource, ArrayList<BaoCaoModel> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView =LayoutInflater.from(context).inflate(resource, null);

        TextView tvSTT = convertView.findViewById(R.id.tvSTT);
        TextView tvLoaiPhong = convertView.findViewById(R.id.tvLoaiPhong);
        TextView tvTang = convertView.findViewById(R.id.tvTang);
        TextView tvNgaySD = convertView.findViewById(R.id.tvNgaySD);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);

        BaoCaoModel baoCao = data.get(position);

        tvSTT.setText(baoCao.getSTT() + "");
        tvLoaiPhong.setText(baoCao.getLoaiPhong());
        tvTang.setText(baoCao.getTang() + "");
        tvNgaySD.setText(baoCao.getNgaySD());
        tvSoLuong.setText(baoCao.getSoLuong() + "");

        return convertView;
    }
}
