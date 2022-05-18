package com.example.nhom28_giuakyquanlythietbitruonghoc.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class chiTietThietBiHelper extends SQLiteOpenHelper {
    public chiTietThietBiHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //truy van ko tra ket qua
    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);

    }
    public void Insert(String map,String matb, String ngay, int sl,byte [] hinh){
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO CHITIET VALUES(null, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, map);
        sqLiteStatement.bindString(2, matb);
        sqLiteStatement.bindString(3, ngay);
        sqLiteStatement.bindLong(4, sl);
        sqLiteStatement.bindBlob(5, hinh);
        System.out.println(map+matb+ngay+sl+hinh);
        sqLiteStatement.executeInsert();

    }
    public int Update(int id,String map,String matb, String ngay, int sl,byte [] hinh){
        SQLiteDatabase database=getWritableDatabase();
        String sql="UPDATE CHITIET SET MAP=?, MATB=?, NGAY=?, SL=?, HINHANH=? WHERE ID=?";
        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
       // sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1, map);
        sqLiteStatement.bindString(2, matb);
        sqLiteStatement.bindString(3, ngay);
        sqLiteStatement.bindLong(4, sl);
        sqLiteStatement.bindBlob(5, hinh);
        sqLiteStatement.bindLong(6, id);
       return  sqLiteStatement.executeUpdateDelete();

    }

    //truy van tra ket qua
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
