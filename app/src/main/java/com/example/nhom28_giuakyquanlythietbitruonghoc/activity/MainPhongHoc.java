package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.CustomApdapterPH;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.PhongHocModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;


public class MainPhongHoc extends AppCompatActivity {

    Button btnThem, btnXoa, btnSua;
    EditText txtMaPhong, txtLoaiPhong, txtTang;
    ListView lvDanhSach;

    int index = -1;

    CustomApdapterPH apdapter;

    ArrayList<PhongHocModel> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phong_hoc);

        setControl();
        setEvent();
        ActionBar actionBar1 = getSupportActionBar();
        actionBar1.setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    private void setEvent() {
        HienThiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                HienThiDL();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaDL();
                HienThiDL();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaDL();
                HienThiDL();
            }
        });
       lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               PhongHocModel phongHoc = data.get(position);
               txtMaPhong.setText(phongHoc.getMAPHONG());
               txtMaPhong.setEnabled(false);
               txtLoaiPhong.setText(phongHoc.getLOAIPHONG());
               txtTang.setText(String.valueOf(phongHoc.getTANG()));
             index = position;

           }
        });

    }




    private void HienThiDL() {
        DBHelper dbPhongHoc = new DBHelper(this);
        data = dbPhongHoc.LayDLPH();
        apdapter = new CustomApdapterPH(MainPhongHoc.this, R.layout.listview_item_ph, data);
        lvDanhSach.setAdapter(apdapter);
    }

    private void ThemDL() {
        PhongHocModel phongHoc = new PhongHocModel();
        phongHoc.setMAPHONG(txtMaPhong.getText().toString());
        phongHoc.setLOAIPHONG(txtLoaiPhong.getText().toString());
        phongHoc.setTANG(Integer.parseInt(txtTang.getText().toString()));

        DBHelper dbPhongHoc = new DBHelper(this);
        dbPhongHoc.ThemPH(phongHoc);
    }
    private void SuaDL() {
        PhongHocModel phongHoc = new PhongHocModel();
        phongHoc.setMAPHONG(txtMaPhong.getText().toString());
        phongHoc.setLOAIPHONG(txtLoaiPhong.getText().toString());
        phongHoc.setTANG(Integer.parseInt(txtTang.getText().toString()));
        DBHelper dbPhongHoc = new DBHelper(this);
        dbPhongHoc.SuaPH(phongHoc);
    }
    private void XoaDL() {
        PhongHocModel phongHoc = new PhongHocModel();
        phongHoc.setMAPHONG(txtMaPhong.getText().toString());
        phongHoc.setLOAIPHONG(txtLoaiPhong.getText().toString());
        phongHoc.setTANG(Integer.parseInt(txtTang.getText().toString()));
        DBHelper dbPhongHoc = new DBHelper(this);
        dbPhongHoc.XoaPH(phongHoc);
    }


    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        txtTang = findViewById(R.id.txtTang);
        txtLoaiPhong = findViewById(R.id.txtLoaiPhong);
        txtMaPhong = findViewById(R.id.txtMaPhong);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}