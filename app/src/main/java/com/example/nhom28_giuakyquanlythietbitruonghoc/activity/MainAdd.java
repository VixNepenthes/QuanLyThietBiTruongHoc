package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.*;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.YuvImage;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiAdapter;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.database.DBHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.chiTietThietBi;

public class MainAdd extends AppCompatActivity {
    com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiHelper chiTietThietBiHelper;
    ArrayList<chiTietThietBi> arrayList;
    chiTietThietBiAdapter adapter;
    Button btnAdd,btnHuy;
    ImageView etDate;
    ImageView imgButton, img;
    ActivityResultLauncher<String> nTakePhoto;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add);
        setControll();
        nTakePhoto=registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        img.setImageURI(result);
                    }
                }
        );
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nTakePhoto.launch("image/*");
            }
        });

        etDate= (ImageView) findViewById(R.id.etDate);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainAdd.this,MainChiTiet.class);
                startActivity(i);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText maP=(EditText) findViewById(R.id.maP);
                EditText maTB=(EditText) findViewById(R.id.maTB);
                EditText Ngay=(EditText) findViewById(R.id.ngay);
                EditText SL=(EditText) findViewById(R.id.soluong);
                ImageView img=(ImageView) findViewById(R.id.img);
                ImageView imgButton=(ImageView) findViewById(R.id.selectImg);
                String map=maP.getText().toString().trim();
                String matb=maTB.getText().toString().trim();
                String ngay=Ngay.getText().toString().trim();
                String kqsl=SL.getText().toString().trim();

                //chuyen hinh anh ve byte
                BitmapDrawable bitmapDrawable= (BitmapDrawable) img.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG.PNG,100,byteArray);
                byte[] hinhAnh=byteArray.toByteArray();
                if(TextUtils.isEmpty(map) ||TextUtils.isEmpty(matb)||TextUtils.isEmpty(ngay)||TextUtils.isEmpty(kqsl)){
                    Toast.makeText(MainAdd.this, "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!kqsl.matches("-?\\d+(\\.\\d+)?")){
                    Toast.makeText(MainAdd.this, "Vui lòng nhập đúng số lượng", Toast.LENGTH_SHORT).show();
                    SL.setText("");
                    return;
                }
                chiTietThietBiHelper=new chiTietThietBiHelper(MainAdd.this,"chiTietThietBi.sqlite",null,1);
                //tao table

                chiTietThietBiHelper.QueryData("CREATE TABLE IF NOT EXISTS CHITIET (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAP VARCHAR(100),MATB VARCHAR(100),NGAY VARCHAR(100),SL INTEGER, HINHANH BLOG)");
                //hien thi
                int sl=Integer.parseInt(kqsl);
                System.out.println("SAI: "+ map+matb+ngay+sl+hinhAnh);
                chiTietThietBiHelper.Insert(map,matb,ngay,sl,hinhAnh);
                Toast.makeText(MainAdd.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainAdd.this, MainChiTiet.class);
                startActivity(i);
            }
        });
    }
    private void setControll() {

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnHuy=(Button) findViewById(R.id.btnhuy);
         img=(ImageView) findViewById(R.id.img);
         imgButton=(ImageView) findViewById(R.id.selectImg);
    }
    private  void ChonNgay(){
        Calendar calendar=Calendar.getInstance();
        int ngay=calendar.get(Calendar.DATE);
        int thang=calendar.get(Calendar.MONTH);
        int nam=calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                EditText Ngay=(EditText) findViewById(R.id.ngay);
                Ngay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}