package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.ThongKe2Adapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.PhongHocModel;

import java.util.ArrayList;
import java.util.List;

public class ThongKe2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ThongKe2Adapter thongKe2Adapter;
    ScrollView scrollView;
    List<PhongHocModel> list;
    DBHelper dbHelper = new DBHelper(ThongKe2Activity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke2);
        initUI();
        setEvent();
    }
    public void initUI(){
        recyclerView = findViewById(R.id.recycle_view_tke2);
        scrollView = findViewById(R.id.scroll_view_tke2);
    }
    public void setEvent(){
        list = new ArrayList<PhongHocModel>();
//        for (int i=0;i<=50;i++){
//            list.add(new PhongHocModel("00"+i,"VIP",2));
//        }
        //list = dbHelper.resultQuery2();

        list = dbHelper.resultQuery2();
        thongKe2Adapter = new ThongKe2Adapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thongKe2Adapter);
    }
}
