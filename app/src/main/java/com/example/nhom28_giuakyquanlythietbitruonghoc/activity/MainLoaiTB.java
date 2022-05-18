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

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.CustomApdapterLTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.LoaiThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;

public class MainLoaiTB extends AppCompatActivity {
    Button btnThem, btnXoa, btnSua;
    EditText txtMaLoai, txtTenLoai;
    ListView lvDanhSach;

    int index = -1;

    CustomApdapterLTB apdapter;

    ArrayList<LoaiThietBiModel> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loai_t_b);

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
                LoaiThietBiModel loaithietbi = data.get(position);
                txtMaLoai.setText(loaithietbi.getMALOAI());
                txtMaLoai.setEnabled(false);
                txtTenLoai.setText(loaithietbi.getTENLOAI());
                index = position;

            }
        });

    }




    private void HienThiDL() {
        DBHelper dbLoaiThietBi = new DBHelper(this);
        data = dbLoaiThietBi.LayDLLTB();
        apdapter = new CustomApdapterLTB(MainLoaiTB.this, R.layout.listview_item, data);
        lvDanhSach.setAdapter(apdapter);
    }

    private void ThemDL() {
        LoaiThietBiModel loaithietbi = new LoaiThietBiModel();
        loaithietbi.setMALOAI(txtMaLoai.getText().toString());
        loaithietbi.setTENLOAI(txtTenLoai.getText().toString());

        DBHelper dbLoaiThietBi = new DBHelper(this);
        dbLoaiThietBi.ThemLTB(loaithietbi);
    }
    private void SuaDL() {
        LoaiThietBiModel loaithietbi = new LoaiThietBiModel();
        loaithietbi.setMALOAI(txtMaLoai.getText().toString());
        loaithietbi.setTENLOAI(txtTenLoai.getText().toString());
        DBHelper dbLoaiThietBi = new DBHelper(this);
        dbLoaiThietBi.SuaLTB(loaithietbi);
    }
    private void XoaDL() {
        LoaiThietBiModel loaithietbi = new LoaiThietBiModel();
        loaithietbi.setMALOAI(txtMaLoai.getText().toString());
        loaithietbi.setTENLOAI(txtTenLoai.getText().toString());
        DBHelper dbLoaiThietBi = new DBHelper(this);
        dbLoaiThietBi.XoaLTB(loaithietbi);
    }


    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        txtMaLoai = findViewById(R.id.txtMaLoai);
        txtTenLoai = findViewById(R.id.txtTenLoai);
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
