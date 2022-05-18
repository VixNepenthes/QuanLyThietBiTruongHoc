package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.ThongKe1Adapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe1Model;

import java.util.ArrayList;

public class ThongKe1Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ThongKe1Adapter thongKe1Adapter;
    ScrollView scrollView;
    ArrayList<ThongKe1Model> thongKe1Models;
    DBHelper dbHelper= new DBHelper(ThongKe1Activity.this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke1);
        initUI();
        setEvent();

    }
    public void initUI(){
        recyclerView = findViewById(R.id.recycle_view_tke1);
        scrollView = findViewById(R.id.scroll_view_tke1);
    }
    public void setEvent(){

        thongKe1Models = new ArrayList<ThongKe1Model>();
        thongKe1Models = dbHelper.resultQuery1();

        thongKe1Adapter = new ThongKe1Adapter(thongKe1Models,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(thongKe1Adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
