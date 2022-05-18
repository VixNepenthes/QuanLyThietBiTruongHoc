package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe1Model;

import java.util.List;

public class ThongKe1Adapter extends RecyclerView.Adapter<ThongKe1Adapter.ViewHolder> {
    //Dữ liệu hiển thị là danh sách kết quả trả về của Cursor query mục 1
    private List<ThongKe1Model> mTke1;
    //Lưu Context để dễ dàng truy cập
    private Context mContext;

    public ThongKe1Adapter(List mTke1,Context mContext){
        this.mTke1=mTke1;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        //Nạp layout cho View biểu diễn phần tử kết quả
        View tke1View = inflater.inflate(R.layout.thongke1_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(tke1View);
        return viewHolder;
        //return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.thongke1_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mTke1!=null && mTke1.size()>0) {
            ThongKe1Model thongKe1Model = mTke1.get(position);
            holder.maphong.setText(thongKe1Model.getMAPHONG());
            holder.loaiphong.setText(thongKe1Model.getLOAIPHONG());
            holder.thietbi.setText(thongKe1Model.getTENTB());
            holder.loaithietbi.setText(thongKe1Model.getTENLOAI());
            holder.soluong.setText( String.valueOf(thongKe1Model.getSOLUONG()));
        }
        else return;
//        holder.item_thongke1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),holder.maphong.getText()+" | ")
//            }
//        });
    }



    @Override
    public int getItemCount() {
        if(mTke1!=null) {
            return mTke1.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        public TextView maphong;
        public TextView loaiphong;
        public TextView thietbi;
        public TextView loaithietbi;
        public TextView soluong;
        TableLayout item_thongke1;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maphong = itemView.findViewById(R.id.tv_maph1);
            loaiphong = itemView.findViewById(R.id.tv_loaiph1);
            thietbi = itemView.findViewById(R.id.tv_tentb1);
            loaithietbi = itemView.findViewById(R.id.tv_tenloaitb1);
            soluong = itemView.findViewById(R.id.tv_soluong1);
            item_thongke1 = itemView.findViewById(R.id.item_thongke1);
        }
    }
}
