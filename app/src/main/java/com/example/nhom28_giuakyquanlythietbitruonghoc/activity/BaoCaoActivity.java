package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.BaoCaoAdapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.BaoCaoModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BaoCaoActivity extends AppCompatActivity {

    Spinner spnLocTheo, spnChonThietBi;
    Button btnLoc, btnIn;
    TextView tvTongSoLuong, tvDate;

    ListView lvBaoCao;
    ArrayList<BaoCaoModel> data = new ArrayList<BaoCaoModel>();
    BaoCaoAdapter adapter;

    DBHelper dbHelper = new DBHelper(BaoCaoActivity.this);

    LinearLayout linear;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baocao);
        setTitle("Báo Cáo Thống Kê");

        setControl();
        khoitao();
        setEvent();
    }

    private void khoitao() {
        loadSpinerLocTheo();
        loadDate();

    }

    private void loadDate() {
        Date date = Calendar.getInstance().getTime();
        String day          = (String) DateFormat.format("dd",   date);
        String monthNumber  = (String) DateFormat.format("MM",   date);
        String year         = (String) DateFormat.format("yyyy", date);

        String currentDate = "TPHCM, ngày " + day + " tháng " + monthNumber + " năm " + year;
        tvDate.setText(currentDate);

    }

    private void setControl() {
        spnLocTheo = findViewById(R.id.spnLocTheo);
        spnChonThietBi = findViewById(R.id.spnChonThietBi);
        btnLoc = findViewById(R.id.btnLoc);
        btnIn = findViewById(R.id.btnIn);
        tvTongSoLuong = findViewById(R.id.tvTongSoLuong);
        tvDate = findViewById(R.id.tvDate);
        lvBaoCao = findViewById(R.id.lvBaoCao);
        linear = findViewById(R.id.linearBaoCao);
    }

    private void setEvent() {
        spnLocTheo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                if(text.equals("Loại thiết bị")){
                    loadSpnChonThietBi(1);
                }
                else if(text.equals("Thiết bị")){
                    loadSpnChonThietBi(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spnChonThietBi.getSelectedItem().toString();
                text = text.substring(0, text.indexOf(" "));
                loadListViewBaoCao(text);
                loadTongSoLuong();
            }
        });

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("size", ""+linear.getHeight() + " " + linear.getWidth());
                bitmap = LoadBitmap(linear, linear.getWidth(), linear.getHeight());
                createPDF();
            }
        });

    }

    private Bitmap LoadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return  bitmap;
    }

    private void createPDF(){
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        int convertWidth = (int)width, convertHeight = (int)height;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);

        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        String targetPDF = "/sdcard/report.pdf";

        File file;
        file = new File(targetPDF);
        try {
            document.writeTo(new FileOutputStream(file));
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "sai o day1", Toast.LENGTH_SHORT).show();

        }
        document.close();
        Toast.makeText(this, "success save to SDcard", Toast.LENGTH_SHORT).show();
    }

    private void loadListViewBaoCao(String keyword){
        data = dbHelper.readBaoCao(keyword);
        adapter = new BaoCaoAdapter(BaoCaoActivity.this, R.layout.item_listview_layout, data);
        lvBaoCao.setAdapter(adapter);
    }

    private void loadTongSoLuong(){
        tvTongSoLuong.setText(dbHelper.getTongSL() + "");
    }

    private void loadSpnChonThietBi(int choose){
        ArrayList<String> arrayData = new ArrayList<String>();
        if(choose == 1){//loc theo loai thiet bi
            arrayData =dbHelper.readLoaiThietBi();
        }else if(choose == 2){//loc theo thiet bi
            arrayData =dbHelper.readThietBi();
        }
        ArrayAdapter adapterChonTB = new ArrayAdapter(BaoCaoActivity.this, android.R.layout.simple_spinner_item, arrayData);
        adapterChonTB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnChonThietBi.setAdapter(adapterChonTB);
    }

    private void loadSpinerLocTheo() {
        ArrayList<String> arrayLocTheo = new ArrayList<String>();
        arrayLocTheo.add("Loại thiết bị");
        arrayLocTheo.add("Thiết bị");

        ArrayAdapter adapterLocTheo = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayLocTheo);
        adapterLocTheo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLocTheo.setAdapter(adapterLocTheo);
    }


}