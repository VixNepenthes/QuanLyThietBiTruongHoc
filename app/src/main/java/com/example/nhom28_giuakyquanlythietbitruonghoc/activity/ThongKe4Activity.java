package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.ThongKe4Adapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe1Model;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe4Model;

import java.util.ArrayList;
import java.util.List;

public class ThongKe4Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ScrollView scrollView;
    List<ThongKe4Model> list;
    DBHelper dbHelper = new DBHelper(ThongKe4Activity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke4);
        initUi();
        setEvent();
    }
    public void initUi(){
        recyclerView = findViewById(R.id.recycle_view_tke4);
        scrollView = findViewById(R.id.scroll_view_tke4);
    }

    public void setEvent(){
        list = new ArrayList<ThongKe4Model>();

//        for (int i=1;i<=50;i++){
//           list.add(new ThongKe4Model("00"+i,"VIP","Den","Chieu",i ));
//        }

         list = dbHelper.resultQuery4();
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(new ThongKe4Adapter(list,this));
    }
}
