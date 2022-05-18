package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.CustomApdapterTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;

import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;


public class MainTB extends AppCompatActivity {
    Button btnThem, btnXoa, btnSua;
    EditText txtMaTBi, txtTenTBi, txtXuatXu;
    Spinner spMaLoai;
    ListView lvDanhSach;

    int index = -1;

    CustomApdapterTB apdapter;
    ArrayList<String> data_MaLoai = new ArrayList<>();
    ArrayAdapter adapter_MaLoai;

    ArrayList<ThietBiModel> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_t_b);

        setControl();
        setEvent();
        ActionBar actionBar1 = getSupportActionBar();
        actionBar1.setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }
    private void setEvent() {
        LoadSpinner();
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
                ThietBiModel thietbi = data.get(position);
                txtMaTBi.setText(thietbi.getMATB());
                txtMaTBi.setEnabled(false);
                txtTenTBi.setText(thietbi.getTENTB());
                txtXuatXu.setText(thietbi.getXUATXU());
                spMaLoai.setSelection(index);
                index = position;

            }
        });

    }
    private void LoadSpinner() {
        DBHelper dbLoaiThietBi = new DBHelper(this);
        data_MaLoai = dbLoaiThietBi.LayMaLoai();
        adapter_MaLoai = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data_MaLoai);
        spMaLoai.setAdapter(adapter_MaLoai);

    }

    private void HienThiDL() {
        DBHelper dbThietBi = new DBHelper(this);
        data = dbThietBi.LayDLTB();
        apdapter = new CustomApdapterTB(MainTB.this, R.layout.listview_item_tb, data);
        lvDanhSach.setAdapter(apdapter);
    }

    private void ThemDL() {
        ThietBiModel thietbi = new ThietBiModel();
        thietbi.setMATB(txtMaTBi.getText().toString());
        thietbi.setTENTB(txtTenTBi.getText().toString());
        thietbi.setXUATXU(txtXuatXu.getText().toString());
        thietbi.setMALOAI(spMaLoai.getSelectedItem().toString());
        DBHelper dbThietBi = new DBHelper(this);
        dbThietBi.ThemTB(thietbi);
    }
    private void SuaDL() {
        ThietBiModel thietbi = new ThietBiModel();
        thietbi.setMATB(txtMaTBi.getText().toString());
        thietbi.setTENTB(txtTenTBi.getText().toString());
        thietbi.setXUATXU(txtXuatXu.getText().toString());
        thietbi.setMALOAI(spMaLoai.getSelectedItem().toString());

        DBHelper dbThietBi = new DBHelper(this);
        dbThietBi.SuaTB(thietbi);
    }
    private void XoaDL() {
        ThietBiModel thietbi = new ThietBiModel();
        thietbi.setMATB(txtMaTBi.getText().toString());
        thietbi.setTENTB(txtTenTBi.getText().toString());
        thietbi.setXUATXU(txtXuatXu.getText().toString());
        thietbi.setMALOAI(spMaLoai.getSelectedItem().toString());
        DBHelper dbThietBi = new DBHelper(this);
        dbThietBi.XoaTB(thietbi);
    }


    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        txtMaTBi = findViewById(R.id.txtMaTB);
        txtTenTBi = findViewById(R.id.txtTenTB);
        txtXuatXu = findViewById(R.id.txtXuatXu);
        spMaLoai = findViewById(R.id.spMaLoai);
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