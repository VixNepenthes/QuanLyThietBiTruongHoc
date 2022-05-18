package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.Top10Model;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeVer2Activity extends AppCompatActivity {

    ArrayList<BarEntry> visitors;
    BarChart barChart;
    Spinner spinner;
    ArrayList<String> spnList;
    LinearLayout layout;
    ArrayList<Integer> color ;


    DBHelper db = new DBHelper(ThongKeVer2Activity.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongke);
        initUi();
        color = new ArrayList<Integer>();
        color.add(Color.RED);
        color.add(Color.BLACK);
        color.add(Color.BLUE);
        color.add(Color.GREEN);
        color.add(Color.YELLOW);
        color.add(Color.MAGENTA);
        color.add(Color.CYAN);
        color.add(Color.RED);
        color.add(Color.BLUE);
        color.add(Color.YELLOW);


        //barChart = findViewById(R.id.barChartTke1);
       loadSpiner();
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String text = adapterView.getItemAtPosition(i).toString();
               if (text.equals("Top 10 thiết bị được sử dụng nhiều nhất")){
                    ArrayList<Top10Model> list = new ArrayList<Top10Model>();
                    list = db.Top10();
                   barChart = new BarChart(ThongKeVer2Activity.this);
                   barChart.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
                   visitors = new ArrayList<BarEntry>();
                   BarData barData = new BarData();
                   for (int j=1;j<=list.size(); j++){
                       ArrayList<BarEntry> barEntries= new ArrayList<BarEntry>();
                       Top10Model thietbi = list.get(j-1);
                       barEntries.add(new BarEntry(j,thietbi.getSl()));
                       BarDataSet barDataSet = new BarDataSet(barEntries,thietbi.getName());
                       barDataSet.setColor(color.get(j));
                       barDataSet.setValueTextSize(16f);
                       barData.addDataSet(barDataSet);

                   }
                   barChart.setData(barData);

                   barChart.getDescription().setEnabled(false);
                   barChart.animate();

                   layout.addView(barChart);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    }
    public void initUi(){
        spinner = findViewById(R.id.spnChonThongKe);
        layout = findViewById(R.id.layoutChart);

    }
    public void loadSpiner(){
        spnList = new ArrayList<String>();
        spnList.add(" ");
        spnList.add("Top 10 thiết bị được sử dụng nhiều nhất");
        spnList.add("Top 3 phòng học sử dụng nhiều thiết bị nhất");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThongKeVer2Activity.this, android.R.layout.simple_spinner_item,spnList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
