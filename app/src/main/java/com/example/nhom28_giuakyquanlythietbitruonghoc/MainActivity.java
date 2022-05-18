package com.example.nhom28_giuakyquanlythietbitruonghoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.ActionBar;

import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.BaoCaoActivity;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.LoginActivity;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainChiTiet;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainLoaiTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainPhongHoc;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.MainTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.activity.ThongKeActivity;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiHelper;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {
    FirebaseAuth auth;
    Button chiTiet,thongKe,btnLogout,baoCao;
    Button btnTB, btnLoaiTB, btnPhongHoc;

    chiTietThietBiHelper chiTietThietBiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){

        }
        chiTiet=(Button) findViewById(R.id.chiTiet);
        thongKe=(Button) findViewById(R.id.thongKe);
        btnLogout = findViewById(R.id.btnLogOut);
        baoCao= findViewById(R.id.baoCao);
        btnTB = findViewById(R.id.btnTB);
        btnLoaiTB = findViewById(R.id.btnLoaiTB);
        btnPhongHoc = findViewById(R.id.btnPhongHoc);

        btnLoaiTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainLoaiTB.class);
                startActivity(intent);
            }
        });
        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainTB.class);
                startActivity(intent);
            }
        });

        btnPhongHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainPhongHoc.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    //   progressDialog.dismiss();
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }


        });
        chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, MainChiTiet.class);
                startActivity(i);
            }
        });
        thongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(i);
            }
        });
        baoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this, BaoCaoActivity.class);
                startActivity(i);
            }
        });
    }
}