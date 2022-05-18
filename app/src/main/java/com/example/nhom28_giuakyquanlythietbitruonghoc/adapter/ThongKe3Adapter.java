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
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe3Model;

import java.util.List;

public class ThongKe3Adapter extends RecyclerView.Adapter<ThongKe3Adapter.ViewHolder> {
    List<ThongKe3Model> list;
    Context context;


    public ThongKe3Adapter(List list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.thongke3_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKe3Adapter.ViewHolder holder, int position) {
        if (list!=null && list.size()>0) {
            ThongKe3Model thongKe3Model = list.get(position);
            holder.maph.setText(thongKe3Model.getMaph());
            holder.loaiph.setText(thongKe3Model.getLoaiph());
            holder.tb1.setText(thongKe3Model.getTb1());
            holder.tb2.setText(thongKe3Model.getTb2());
        }
        else return;
    }

    @Override
    public int getItemCount() {
        if (list!= null){
            return list.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maph,loaiph,tb1,tb2;
        TableLayout item_tke3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maph = itemView.findViewById(R.id.tv_maph3);
            loaiph = itemView.findViewById(R.id.tv_loaiph3);
            tb1 = itemView.findViewById(R.id.tv_tb1_3);
            tb2 = itemView.findViewById(R.id.tv_tb2_3);
            item_tke3 = itemView.findViewById(R.id.item_thongke3);
        }
    }
}
