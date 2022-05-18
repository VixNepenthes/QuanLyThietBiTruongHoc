package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.ThongKe2Adapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.ThongKe3Adapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe3Model;

import java.util.ArrayList;
import java.util.List;

public class ThongKe3Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ScrollView scrollView;
    List<ThongKe3Model> list;
    ThongKe3Adapter thongKe3Adapter;
    DBHelper dbHelper = new DBHelper(ThongKe3Activity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke3);
        initUi();
        setEvent();
    }
    public void initUi(){
        recyclerView = findViewById(R.id.recycle_view_tke3);
        scrollView = findViewById(R.id.scroll_view_tke3);
    }
    public void setEvent(){
        list = new ArrayList<ThongKe3Model>();
//        for(int i=1;i<=50;i++){
//            list.add(new ThongKe3Model("00"+i,"VIP","Den","Quat"));
//        }
        list = dbHelper.resultQuery3();
        thongKe3Adapter = new ThongKe3Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thongKe3Adapter);
    }
}
