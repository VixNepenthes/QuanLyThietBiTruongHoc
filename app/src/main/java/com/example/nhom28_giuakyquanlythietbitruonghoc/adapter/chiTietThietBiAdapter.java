package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainChiTiet;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.chiTietThietBi;

import java.util.ArrayList;
import java.util.List;

public class chiTietThietBiAdapter extends BaseAdapter {
    private MainChiTiet context;
    private int layout;
    private List<chiTietThietBi> chiTietThietBiList;


    public chiTietThietBiAdapter(MainChiTiet context, int layout, ArrayList<chiTietThietBi> chiTietThietBiList) {
        this.context=context;
        this.layout = layout;
        this.chiTietThietBiList = chiTietThietBiList;
    }

    @Override
    public int getCount() {
        return chiTietThietBiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView tvMap;
        TextView tvMatb;
        TextView tvNgay;
        TextView tvSl;
        ImageView imgSua,imgXoa,imgHinhAnh;
       // Button them;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.tvMap=(TextView) view.findViewById(R.id.tvMap);
            holder.tvMatb=(TextView) view.findViewById(R.id.tvMatb);
            holder.tvNgay=(TextView) view.findViewById(R.id.tvNgay);
            holder.tvSl=(TextView) view.findViewById(R.id.tvSl);
            holder.imgHinhAnh=(ImageView) view.findViewById(R.id.imgHinhAnh);

            holder.imgSua=(ImageView) view.findViewById(R.id.imgSua);
            holder.imgXoa=(ImageView) view.findViewById(R.id.imgXoa);
            view.setTag(holder);

        }
        else{
            holder=(ViewHolder) view.getTag();
        }
        chiTietThietBi chiTietThietBi=chiTietThietBiList.get(i);
        holder.tvMap.setText(chiTietThietBi.getMaP());
        holder.tvMatb.setText(chiTietThietBi.getMaTB());
        holder.tvNgay.setText(chiTietThietBi.getNgay());
        String sls=Integer.toString(chiTietThietBi.getSl());
        holder.tvSl.setText(sls);
        //chuyen byte sag bitmap
        byte [] hinhanhhienthi=chiTietThietBi.getHinh();
        Bitmap bitmap=BitmapFactory.decodeByteArray(hinhanhhienthi,0,hinhanhhienthi.length);
        holder.imgHinhAnh.setImageBitmap(bitmap);
        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              context.DiaLogUpdate(chiTietThietBi.getId(),chiTietThietBi.getMaP(),chiTietThietBi.getMaTB(),chiTietThietBi.getNgay(),chiTietThietBi.getSl(),chiTietThietBi.getHinh());
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DiaLogDelete(chiTietThietBi.getId(),chiTietThietBi.getMaTB(),chiTietThietBi.getMaP());

            }
        });
        return view;
    }
}
