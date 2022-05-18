package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom28_giuakyquanlythietbitruonghoc.MainActivity;
import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiAdapter;
import com.example.nhom28_giuakyquanlythietbitruonghoc.adapter.chiTietThietBiHelper;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.chiTietThietBi;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainChiTiet extends AppCompatActivity {
    public static chiTietThietBiHelper chiTietThietBiHelper;
    ListView lv;
    ArrayList<chiTietThietBi> arrayList;
    chiTietThietBiAdapter adapter;
    Button btnThem,btnThoat;
    EditText edtSearch;
    ImageView imgSearch,imgButton, img,editImg,editAnh;
    ImageView etDate;
    ActivityResultLauncher<String> nTakePhoto;
    private int dialog_sua;
    Spinner spinnerUpdate;
    int aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        setControll();
        nTakePhoto=registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        editAnh.setImageURI(result);
                    }
                }
        );
        //add
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainChiTiet.this,MainAdd.class);
                startActivity(i);
            }
        });

        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        ArrayList<String> arraymenu=new ArrayList<String>();
        arraymenu.add("Chi Tiết");
        arraymenu.add("Thoát");
        arraymenu.add("Thêm");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,arraymenu);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kq=arraymenu.get(i);
                if(kq.equals("Thoát")){
                    Intent a= new Intent(MainChiTiet.this, MainActivity.class);
                    startActivity(a);
                } if(kq.equals("Thêm")){
                    Intent b= new Intent(MainChiTiet.this,MainAdd.class);
                    startActivity(b);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainChiTiet.this, MainActivity.class);
                startActivity(i);
            }
        });
        // search
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung=edtSearch.getText().toString().trim();
                if(TextUtils.isEmpty(noidung)){
                    Toast.makeText(MainChiTiet.this, "Nhap noi dung tim kiem", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor dataChiTietThietBi=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET WHERE MAP LIKE '%"+noidung+"%' OR  MATB LIKE '%"+noidung+"%' OR NGAY LIKE '%"+noidung+"%' ");
                arrayList.clear();
                while (dataChiTietThietBi.moveToNext()){
                    int id=dataChiTietThietBi.getInt(0);
                    String maP=dataChiTietThietBi.getString(1);
                    String maTB=dataChiTietThietBi.getString(2);
                    String ngay = dataChiTietThietBi.getString(3);
                    int sl=dataChiTietThietBi.getInt(4);
                    byte[] hinhanh=dataChiTietThietBi.getBlob(5);
                  arrayList.add(new chiTietThietBi(id,maP,maTB,ngay,sl,hinhanh));

                }
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
            }
        });

        arrayList=new ArrayList<>();

        adapter=new chiTietThietBiAdapter(MainChiTiet.this,R.layout.dong_noi_dung,arrayList);
        lv.setAdapter(adapter);
        //tao database
        chiTietThietBiHelper=new chiTietThietBiHelper(this,"chiTietThietBi.sqlite",null,1);
        //tao table

        chiTietThietBiHelper.QueryData("CREATE TABLE IF NOT EXISTS CHITIET (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAP VARCHAR(100),MATB VARCHAR(100),NGAY VARCHAR(100),SL INTEGER, HINHANH BLOG)");
        //hien thi
        try {
            actionGetData();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    //update
    public void DiaLogUpdate(int Id,String map, String matb, String ngay, int sll,byte[] hinhanh){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        spinnerUpdate=(Spinner) dialog.findViewById(R.id.spinner_sua);
        EditText edtMaP=(EditText) dialog.findViewById(R.id.edtmaP);
        EditText edtMaTB=(EditText) dialog.findViewById(R.id.edtmaTB);
        EditText edtNgay=(EditText) dialog.findViewById(R.id.edtngay);
        EditText edtSl=(EditText) dialog.findViewById(R.id.edtsoluong);
        ImageView editImg=(ImageView) dialog.findViewById(R.id.editImg);
        ImageView eImg=(ImageView) dialog.findViewById(R.id.eImg);
        Button btnSua=(Button) dialog.findViewById(R.id.btnUpdate);
        Button btnHuy=(Button) dialog.findViewById(R.id.btnhuy);
        aa=Id;
        edtMaP.setText(map);
        edtMaTB.setText(matb);
        edtNgay.setText(ngay);
        String sl=Integer.toString(sll);
        edtSl.setText(sl);
        //edit anh
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhanh,0,hinhanh.length);
        eImg.setImageBitmap(bitmap);
        editAnh=(ImageView) dialog.findViewById(R.id.eImg);
        editAnh.setImageBitmap(bitmap);

        //spinner
        ArrayList<chiTietThietBi> arrayListSpinner=new ArrayList<>();
        ArrayList<String> ListSpinner=new ArrayList<>();
        Cursor dataChiTietThietBi=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET");
        while (dataChiTietThietBi.moveToNext()){
            int ids=dataChiTietThietBi.getInt(0);
            String maPs=dataChiTietThietBi.getString(1);
            String maTBs=dataChiTietThietBi.getString(2);
            String ngays = dataChiTietThietBi.getString(3);
            int sls=dataChiTietThietBi.getInt(4);
            byte[] hinhanhs=dataChiTietThietBi.getBlob(5);
            arrayListSpinner.add(new chiTietThietBi(ids,maPs,maTBs,ngays,sls,hinhanhs));
            ListSpinner.add(maPs);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,ListSpinner);
        spinnerUpdate.setAdapter(arrayAdapter);
        spinnerUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String flag=ListSpinner.get(i);
                Cursor dataChiTietThietBiFlag=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET WHERE MAP='"+flag+"'");
                while (dataChiTietThietBiFlag.moveToNext()) {
                    int idf = dataChiTietThietBiFlag.getInt(0);
                    String maPf = dataChiTietThietBiFlag.getString(1);
                    String maTBf = dataChiTietThietBiFlag.getString(2);
                    String ngayf = dataChiTietThietBiFlag.getString(3);
                    int slf = dataChiTietThietBiFlag.getInt(4);
                    byte[] hinhanhf = dataChiTietThietBiFlag.getBlob(5);
                    //Toast.makeText(MainChiTiet.this, idf+maPf+maTBf+ngayf, Toast.LENGTH_SHORT).show();
                    edtMaP.setText(maPf);
                    edtMaTB.setText(maTBf);
                    edtNgay.setText(ngayf);
                    String slff=Integer.toString(slf);
                    edtSl.setText(slff);
                    //edit anh
                    Bitmap bitmapf= BitmapFactory.decodeByteArray(hinhanhf,0,hinhanhf.length);
                    eImg.setImageBitmap(bitmapf);
                    editAnh=(ImageView) dialog.findViewById(R.id.eImg);
                    editAnh.setImageBitmap(bitmapf);
                    aa =idf;
                }
                //

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //spinner
        //img

        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAnh=(ImageView) dialog.findViewById(R.id.eImg);
                nTakePhoto.launch("image/*");
            }
        });

        //edit ngay
        etDate= (ImageView) dialog.findViewById(R.id.editDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int ngay=calendar.get(Calendar.DATE);
                int thang=calendar.get(Calendar.MONTH);
                int nam=calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog=new DatePickerDialog(MainChiTiet.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                        edtNgay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMaP=edtMaP.getText().toString().trim();
                String newMaTB=edtMaTB.getText().toString().trim();
                String newNgay=edtNgay.getText().toString().trim();
                String kqnewSL=edtSl.getText().toString().trim();
                //chuyen hinh anh ve byte
                byte[] hinhAnh;
             try{

                 BitmapDrawable bitmapDrawable= (BitmapDrawable) editAnh.getDrawable();
                 Bitmap bitmap=bitmapDrawable.getBitmap();
                 ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                 bitmap.compress(Bitmap.CompressFormat.JPEG.PNG,100,byteArray);
                 hinhAnh=byteArray.toByteArray();

             }  catch (Exception e)  {
                 hinhAnh=hinhanh;
             }

                if(TextUtils.isEmpty(newMaP) || TextUtils.isEmpty(newMaTB) || TextUtils.isEmpty(newNgay)|| TextUtils.isEmpty(kqnewSL)){
                    Toast.makeText(MainChiTiet.this, "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!kqnewSL.matches("-?\\d+(\\.\\d+)?")){
                    Toast.makeText(MainChiTiet.this, "Vui lòng nhập đúng số lượng", Toast.LENGTH_SHORT).show();
                    edtSl.setText("");
                    return;
                }
                int newSL=Integer.parseInt(kqnewSL);
                int kq=  chiTietThietBiHelper.Update(aa,newMaP,newMaTB,newNgay,newSL,hinhAnh);
                if(kq==1){
                    Toast.makeText(MainChiTiet.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                try {
                    actionGetData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        });
        dialog.show();
    }
    public  void Spinner(){
        ArrayList<chiTietThietBi> arrayListSpinner=new ArrayList<>();
        ArrayList<String> ListSpinner=new ArrayList<>();
        Cursor dataChiTietThietBi=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET");
        while (dataChiTietThietBi.moveToNext()){
            int id=dataChiTietThietBi.getInt(0);
            String maP=dataChiTietThietBi.getString(1);
            String maTB=dataChiTietThietBi.getString(2);
            String ngay = dataChiTietThietBi.getString(3);
            int sl=dataChiTietThietBi.getInt(4);
            byte[] hinhanh=dataChiTietThietBi.getBlob(5);
            arrayListSpinner.add(new chiTietThietBi(id,maP,maTB,ngay,sl,hinhanh));
            ListSpinner.add(maP);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,ListSpinner);
        spinnerUpdate.setAdapter(arrayAdapter);
        spinnerUpdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String flag=ListSpinner.get(i);
                Cursor dataChiTietThietBiFlag=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET WHERE MAP='"+flag+"'");
                while (dataChiTietThietBiFlag.moveToNext()) {
                    int idf = dataChiTietThietBiFlag.getInt(0);
                    String maPf = dataChiTietThietBiFlag.getString(1);
                    String maTBf = dataChiTietThietBiFlag.getString(2);
                    String ngayf = dataChiTietThietBiFlag.getString(3);
                    int slf = dataChiTietThietBiFlag.getInt(4);
                    byte[] hinhanhf = dataChiTietThietBiFlag.getBlob(5);
                    Toast.makeText(MainChiTiet.this, idf+maPf+maTBf+ngayf, Toast.LENGTH_SHORT).show();
                    DiaLogUpdate(idf,maPf,maTBf,ngayf,slf,hinhanhf);
                }
               //

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //    //delete
    public  void DiaLogDelete(int id, String maTB,String maP){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa thiết bị mã "+maTB+" mà mã phòng "+maP+" mượn ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainChiTiet.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                chiTietThietBiHelper.QueryData("DELETE FROM CHITIET WHERE ID='"+id+"'");
                try {
                    actionGetData();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void actionGetData() throws ParseException {

        Cursor dataChiTietThietBi=chiTietThietBiHelper.GetData("SELECT * FROM CHITIET");
        arrayList.clear();
        while (dataChiTietThietBi.moveToNext()){
            int id=dataChiTietThietBi.getInt(0);
            String maP=dataChiTietThietBi.getString(1);
            String maTB=dataChiTietThietBi.getString(2);
            String ngay = dataChiTietThietBi.getString(3);
            int sl=dataChiTietThietBi.getInt(4);
            byte[] hinhanh=dataChiTietThietBi.getBlob(5);
            arrayList.add(new chiTietThietBi(id,maP,maTB,ngay,sl,hinhanh));

        }
        adapter.notifyDataSetChanged();
    }

    private void setControll() {
        lv=(ListView) findViewById(R.id.lvNoiDung);
        btnThem=(Button) findViewById(R.id.btnThem);
        btnThoat=(Button) findViewById(R.id.btnThoat);
        imgSearch=(ImageView) findViewById(R.id.imgTimKiem);
        edtSearch=(EditText) findViewById(R.id.edtSearch);
        imgButton=(ImageView) findViewById(R.id.selectImg);
        //editImg=(ImageView) dialog.findViewById(R.id.editImg);
        img=(ImageView) findViewById(R.id.img);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()) {
           case R.id.ph:
               break;
           case R.id.tb:
               break;
           case R.id.ct:
               startActivity(new Intent(MainChiTiet.this,MainChiTiet.class));
               break;
           case R.id.thongke:
               Intent i= new Intent(MainChiTiet.this, ThongKeActivity.class);
               startActivity(i);
               break;
       }
        return super.onOptionsItemSelected(item);
    }
}
