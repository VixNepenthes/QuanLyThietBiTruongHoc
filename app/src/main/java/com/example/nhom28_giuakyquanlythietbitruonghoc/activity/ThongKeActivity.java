package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;

public class ThongKeActivity extends AppCompatActivity {

    Button btnTke1;
    Button btnTke2;
    Button btnTke3;
    Button btnTke4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);

        initUi();
        setEvent();
    }

    public void initUi(){
        btnTke1= findViewById(R.id.btnTke1);
        btnTke2= findViewById(R.id.btnTke2);
        btnTke3= findViewById(R.id.btnTke3);
        btnTke4= findViewById(R.id.btnTke4);
    }
    public void setEvent(){
        btnTke1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this,ThongKe1Activity.class));
            }
        });

        btnTke2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this,ThongKe2Activity.class));
            }
        });

        btnTke3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this,ThongKe3Activity.class));
            }
        });

        btnTke4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity.this,ThongKe4Activity.class));
            }
        });

    }
}
