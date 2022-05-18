package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe4Model;

import java.util.List;

public class ThongKe4Adapter extends RecyclerView.Adapter<ThongKe4Adapter.ViewHolder> {
    List<ThongKe4Model> list;
    Context context;

    public ThongKe4Adapter(List list,Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.thongke4_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKe4Adapter.ViewHolder holder, int position) {
        if(list !=null && list.size()>0){
            ThongKe4Model thongKe4Model = list.get(position);
            holder.maph.setText(thongKe4Model.getMaph());
            holder.loaiph.setText(thongKe4Model.getLoaiph());
            holder.tentb.setText(thongKe4Model.getTentb());
            holder.loaitb.setText(thongKe4Model.getLoaitb());
            holder.tongsoluong.setText(String.valueOf(thongKe4Model.getTongsolansudung()));
        }
        else return;
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maph,loaiph,tentb,loaitb,tongsoluong;
        TableLayout item_tke4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maph = itemView.findViewById(R.id.tv_maph4);
            loaiph = itemView.findViewById(R.id.tv_loaiph4);
           tentb= itemView.findViewById(R.id.tv_tentb4);
            loaitb = itemView.findViewById(R.id.tv_tenloaitb4);
            tongsoluong = itemView.findViewById(R.id.tv_soluong4);
            item_tke4 = itemView.findViewById(R.id.item_thongke4);
        }
    }
}
