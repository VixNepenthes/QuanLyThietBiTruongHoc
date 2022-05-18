package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.PhongHocModel;

import java.util.List;

public class ThongKe2Adapter extends RecyclerView.Adapter<ThongKe2Adapter.ViewHolder>{

    List<PhongHocModel> list;
    Context context;

    public ThongKe2Adapter(List list,Context context){
        this.list = list;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
//        View tke2View = inflater.inflate(R.layout.thongke2_item,parent,false);
//        ThongKe2Adapter.ViewHolder viewHolder = new ThongKe2Adapter.ViewHolder(tke2View);
//        return viewHolder;
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.thongke2_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKe2Adapter.ViewHolder holder, int position) {
        if(list!=null && list.size()>0){
            PhongHocModel phongHocModel = list.get(position);
            holder.maph.setText(phongHocModel.getMAPHONG());
            holder.loaiph.setText(phongHocModel.getLOAIPHONG());
            holder.tang.setText(String.valueOf(phongHocModel.getTANG()));
        }
        else return;
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maph,loaiph,tang;
        TableLayout item_thongke2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maph = itemView.findViewById(R.id.tv_maph2);
            loaiph = itemView.findViewById(R.id.tv_loaiph2);
            tang = itemView.findViewById(R.id.tv_tang2);
            item_thongke2 = itemView.findViewById(R.id.item_thongke2);
        }
    }
}
