package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThietBiModel;

import java.util.ArrayList;

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.CustomApdapterTB;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;

public class MainDetailTB extends AppCompatActivity {
    ListView lvDanhSach;
    ArrayList<ThietBiModel> data = new ArrayList<>();
    CustomApdapterTB adapter;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail_tb);
        ActionBar actionBar1 = getSupportActionBar();
        actionBar1.setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        DBHelper dbThietBi = new DBHelper(this);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        CustomApdapterTB adapter = new CustomApdapterTB(this, R.layout.listview_item_tb, dbThietBi.LayDLTB());
        lvDanhSach.setAdapter(adapter);
        setEvent();

    }

    private void setEvent() {
        DBHelper dbThietBi = new DBHelper(this);
        data = dbThietBi.LayDLTB();
        adapter = new CustomApdapterTB(this, R.layout.listview_item_tb, data);
        lvDanhSach.setAdapter(adapter);
        registerForContextMenu(lvDanhSach);
    }

    //function quay xe
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionsearch, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    lvDanhSach.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }
}
