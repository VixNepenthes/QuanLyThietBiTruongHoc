package com.example.nhom28_giuakyquanlythietbitruonghoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nhom28_giuakyquanlythietbitruonghoc.model.BaoCaoModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.LoaiThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.PhongHocModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThietBiModel;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe1Model;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe3Model;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.ThongKe4Model;
import com.example.nhom28_giuakyquanlythietbitruonghoc.model.Top10Model;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TBTH";

    private static final String LOAITHIETBI_TABLE = "LOAITHIETBI";
    private static final String COLUMN1_MALOAI = "MALOAI";
    private static final String COLUMN1_TENLOAI = "TENLOAI";

    private static final String PHONGHOC_TABLE = "PHONGHOC";
    private static final String COLUM2_MAPHONG = "MAPHONG";
    private static final String COLUM2_LOAIPHONG = "LOAIPHONG";
    private static final String COLUM2_TANG = "TANG";


    private static final String THIETBI_TABLE = "THIETBI";
    private static final String COLUM3_MATB = "MATB";
    private static final String COLUM3_TENTB = "TENTB";
    private static final String COLUM3_XUATXU = "XUATXU";
    private static final String COLUM3_MALOAI = "MALOAI";

    private static final String CHITIETSUDUNG_TABLE = "CHITIETSUDUNG";
    private static final String COLUM4_MAPHONG = "MAPHONG";
    private static final String COLUM4_MATB = "MATB";
    private static final String COLUM4_NGAYSUDUNG = "NGAYSUDUNG";
    private static final String COLUM4_SOLUONG = "SOLUONG";

    private int tongSL;

    public int getTongSL() {
        return tongSL;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + LOAITHIETBI_TABLE + "("
                                + COLUMN1_MALOAI + " TEXT NOT NULL PRIMARY KEY, "
                                + COLUMN1_TENLOAI + " TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE " + PHONGHOC_TABLE + "("
                + COLUM2_MAPHONG + " TEXT NOT NULL PRIMARY KEY, "
                + COLUM2_LOAIPHONG + " TEXT, "
                + COLUM2_TANG + " INTEGER)";
        db.execSQL(query);

        query = "CREATE TABLE " + THIETBI_TABLE + "("
                + COLUM3_MATB + " TEXT NOT NULL PRIMARY KEY, "
                + COLUM3_TENTB + " TEXT, "
                + COLUM3_XUATXU + " TEXT, "
                + COLUM3_MALOAI + " TEXT NOT NULL, "
                + "FOREIGN KEY ("+ COLUM3_MALOAI +") REFERENCES "+LOAITHIETBI_TABLE+"("+COLUMN1_MALOAI+") "
                + "ON UPDATE CASCADE "
                + "ON DELETE CASCADE)";
        db.execSQL(query);

        query = "CREATE TABLE " + CHITIETSUDUNG_TABLE + "("
                + COLUM4_MAPHONG + " TEXT NOT NULL, "
                + COLUM4_MATB + " TEXT NOT NULL, "
                + COLUM4_NGAYSUDUNG + " TEXT, "
                + COLUM4_SOLUONG + " INTEGER, "
                + "PRIMARY KEY("+COLUM4_MAPHONG+", "+COLUM4_MATB+"),"
                + "FOREIGN KEY ("+COLUM4_MAPHONG+") REFERENCES "+PHONGHOC_TABLE+"("+COLUM2_MAPHONG+") ON UPDATE CASCADE ON DELETE CASCADE, "
                + "FOREIGN KEY ("+COLUM4_MATB+") REFERENCES "+THIETBI_TABLE+"("+COLUM3_MATB+") ON UPDATE CASCADE ON DELETE CASCADE)";
        db.execSQL(query);


        //insert data
        query = "INSERT INTO LOAITHIETBI (MALOAI,TENLOAI) VALUES ('CS', 'Chieu sang')";
        db.execSQL(query);
        query = "INSERT INTO LOAITHIETBI (MALOAI,TENLOAI) VALUES ('DC', 'Dung cu day hoc')";
        db.execSQL(query);
        query = "INSERT INTO LOAITHIETBI (MALOAI,TENLOAI) VALUES ('DH', 'Dieu hoa')";
        db.execSQL(query);
        query = "INSERT INTO LOAITHIETBI (MALOAI,TENLOAI) VALUES ('DT', 'Dien tu')";
        db.execSQL(query);

        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('LT01', 'Phong hoc ly thuyet', 1)";
        db.execSQL(query);
        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('LT02', 'Phong hoc ly thuyet', 2)";
        db.execSQL(query);
        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('LT07', 'Phong hoc ly thuyet', 7)";
        db.execSQL(query);
        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('LT08', 'Phong hoc ly thuyet', 8)";
        db.execSQL(query);
        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('TH06', 'Phong hoc thuc hanh', 6)";
        db.execSQL(query);
        query = "INSERT INTO PHONGHOC (MAPHONG,LOAIPHONG,TANG) VALUES ('TH07', 'Phong hoc thuc hanh', 7)";
        db.execSQL(query);

        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('CS01', 'Den dien quang 1.2M', 'Viet Nam', 'CS')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('CS02', 'Den dien quan 0.6M', 'Viet Nam', 'CS')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DC01', 'May chieu', 'Thai Lan', 'DC')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DC02', 'Micro Samsung', 'Han Quoc', 'DC')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DH01', 'May lanh Toshiba 1 ngua', 'Nhat', 'DH')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DH02', 'May lanh LG 2 ngua', 'Han Quoc', 'DH')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DT01', 'May casset Sony', 'Nhat', 'DT')";
        db.execSQL(query);
        query = "INSERT INTO THIETBI (MATB,TENTB,XUATXU,MALOAI) VALUES ('DT02', 'Dia luyen nghe TA', 'Viet Nam', 'DT')";
        db.execSQL(query);

        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT01', 'CS01', '2018-01-15', 3)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT01', 'DC02', '2018-01-15', 1)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT02', 'CS02', '2017-05-01', 4)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT02', 'DC01', '2017-05-01', 1)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT07', 'CS02', '2018-08-06', 5)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT07', 'DH02', '2017-07-25', 3)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT08', 'DT01', '2018-08-05', 1)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('LT08', 'DT02', '2018-08-05', 3)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('TH06', 'CS01', '2018-07-26', 4)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('TH06', 'DH01', '2018-07-25', 2)";
        db.execSQL(query);
        query = "INSERT INTO CHITIETSUDUNG (MAPHONG,MATB,NGAYSUDUNG,SOLUONG) VALUES ('TH07', 'DH01', '2018-09-18', 2)";
        db.execSQL(query);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CHITIETSUDUNG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + THIETBI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PHONGHOC_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LOAITHIETBI_TABLE);
        onCreate(db);
    }

    public ArrayList<BaoCaoModel> readBaoCao(String keyword){
        ArrayList<BaoCaoModel> resultList = new ArrayList<BaoCaoModel>();
        String sql = "SELECT LOAIPHONG, TANG, NGAYSUDUNG, SOLUONG " +
                "FROM CHITIETSUDUNG INNER JOIN PHONGHOC ON CHITIETSUDUNG.MAPHONG = PHONGHOC.MAPHONG " +
                "INNER JOIN THIETBI ON CHITIETSUDUNG.MATB = THIETBI.MATB "+
                "WHERE THIETBI.MALOAI = ? OR THIETBI.MATB = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql, new String[]{keyword, keyword});
        cs.moveToFirst();
        int stt = 1;
        int sum = 0;
        while(!cs.isAfterLast()){
            String loaiPhong = cs.getString(0);
            int tang = cs.getInt(1);
            String ngaysd = cs.getString(2);
            int soluong = cs.getInt(3);
            sum += soluong;
            resultList.add(new BaoCaoModel(stt, loaiPhong, tang, ngaysd, soluong));
            stt++;
            cs.moveToNext();
        }
        cs.close();
        tongSL = sum;
        return  resultList;
    }

    public ArrayList<String> readLoaiThietBi(){
        ArrayList<String> dataList = new ArrayList<String>();
        String sql = "SELECT MALOAI, TENLOAI FROM " + LOAITHIETBI_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String maloai = cs.getString(0);
            String tenLoai = cs.getString(1);
            dataList.add(maloai + " - " + tenLoai);
            cs.moveToNext();
        }
        return dataList;
    }

    public ArrayList<String> readThietBi(){
        ArrayList<String> dataList = new ArrayList<String>();
        String sql = "SELECT MATB, TENTB FROM " + THIETBI_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String matb = cs.getString(0);
            String tentb = cs.getString(1);
            dataList.add(matb + " - " + tentb);
            cs.moveToNext();
        }
        return dataList;
    }

    public ArrayList<ThongKe1Model> resultQuery1(){
        ArrayList<ThongKe1Model> datalist = new ArrayList<ThongKe1Model>();
        String sql ="select CHITIETSUDUNG.MAPHONG, PHONGHOC.LOAIPHONG, THIETBI.TENTB, LOAITHIETBI.TENLOAI, CHITIETSUDUNG.SOLUONG \n" +
                "from  LOAITHIETBI INNER JOIN THIETBI ON LOAITHIETBI.MALOAI = THIETBI.MALOAI" +
                " INNER JOIN CHITIETSUDUNG ON CHITIETSUDUNG.MATB = THIETBI.MATB" +
                " INNER JOIN PHONGHOC ON PHONGHOC.MAPHONG = CHITIETSUDUNG.MAPHONG  ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs =db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String maph = cs.getString(0);
            String loaiph = cs.getString(1);
            String tb = cs.getString(2);
            String loaitb = cs.getString(3);
            int soluong = cs.getInt(4);
            datalist.add(new ThongKe1Model(maph,loaiph,tb,loaitb,soluong));
            cs.moveToNext();
        }
        return datalist;
    }
    public ArrayList<PhongHocModel> resultQuery2(){
        ArrayList<PhongHocModel> datalist = new ArrayList<PhongHocModel>();
        String sql ="select MAPHONG,LOAIPHONG,TANG\n" +
                "from PHONGHOC as P1\n" +
                "where P1.MAPHONG NOT IN (select PHONGHOC.MAPHONG\n" +
                "\t\t\t\t\t\tfrom THIETBI\n" +
                "\t\t\t\t\t\tINNER join CHITIETSUDUNG on THIETBI.MATB = CHITIETSUDUNG.MATB\n" +
                "\t\t\t\t\t\tINNER join PHONGHOC on PHONGHOC.MAPHONG = CHITIETSUDUNG.MAPHONG\n" +
                "\t\t\t\t\t\twhere THIETBI.TENTB like '%Micro%')";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String maph = cs.getString(0);
            String loaiph = cs.getString(1);
            int tang = cs.getInt(2);
            datalist.add(new PhongHocModel(maph,loaiph,tang));
            cs.moveToNext();
        }
        return datalist;

    }

    public ArrayList<ThongKe3Model> resultQuery3(){
        ArrayList<ThongKe3Model> datalist = new ArrayList<ThongKe3Model>();
        String sql =" select MAPHONG, LOAIPHONG, \n" +
                "    case when instr(TenTb,', ')>0 \n" +
                "         then substr (TenTb,1,instr(TenTb,', ')-1) \n" +
                "         else TenTb end LoaiTB1, \n" +
                "    CASE WHEN instr(TenTb,', ')>0 \n" +
                "         THEN substr(TenTb,instr(TenTb,', ')+1,length(TenTb)) \n" +
                "         ELSE NULL END as LoaiTB2 \n" +
                "from ( \n" +
                "select CHITIETSUDUNG.MAPHONG AS MaPhong, PHONGHOC.LOAIPHONG AS LoaiPhong, group_concat (THIETBI.TENTB, ', ') AS TenTb \n" +
                "from  LOAITHIETBI INNER JOIN THIETBI ON LOAITHIETBI.MALOAI = THIETBI.MALOAI \n" +
                "INNER JOIN CHITIETSUDUNG ON CHITIETSUDUNG.MATB = THIETBI.MATB \n" +
                "INNER JOIN PHONGHOC ON PHONGHOC.MAPHONG = CHITIETSUDUNG.MAPHONG \n" +
                "where CHITIETSUDUNG.MAPHONG in ( \n" +
                "select MAPHONG \n" +
                "from CHITIETSUDUNG \n" +
                "group by CHITIETSUDUNG.MAPHONG \n" +
                "having COUNT(MATB) = 2) \n" +
                "group by CHITIETSUDUNG.MAPHONG, PHONGHOC.LOAIPHONG)" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String maph = cs.getString(0);
            String loaiph = cs.getString(1);
            String tb1 = cs.getString(2);
            String tb2 = cs.getString(3);
            datalist.add(new ThongKe3Model(maph,loaiph,tb1,tb2));
            cs.moveToNext();
        }
        return datalist;
    }

    public ArrayList<ThongKe4Model> resultQuery4(){
        ArrayList<ThongKe4Model> datalist = new ArrayList<ThongKe4Model>();
        String sql = "SELECT CHITIETSUDUNG.MAPHONG, PHONGHOC.LOAIPHONG,THIETBI.TENTB, LOAITHIETBI.TENLOAI" +
                ", SUM(CHITIETSUDUNG.SOLUONG) AS TONGSOLUONG " +
                "from  LOAITHIETBI INNER JOIN THIETBI ON LOAITHIETBI.MALOAI = THIETBI.MALOAI " +
                "INNER JOIN CHITIETSUDUNG ON THIETBI.MATB = CHITIETSUDUNG.MATB " +
                "INNER JOIN PHONGHOC ON PHONGHOC.MAPHONG = CHITIETSUDUNG.MAPHONG " +
                "GROUP BY CHITIETSUDUNG.MATB";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String maph = cs.getString(0);
            String loaiph = cs.getString(1);
            String tentb = cs.getString(2);
            String loaitb = cs.getString(3);
            int tongsoluong = cs.getInt(4);
            datalist.add(new ThongKe4Model(maph,loaiph,tentb,loaitb,tongsoluong));
            cs.moveToNext();

        }
        return datalist;
    }

    public ArrayList<PhongHocModel> resultTest(){
        ArrayList<PhongHocModel> list = new ArrayList<PhongHocModel>();
                for (int i=0;i<=50;i++){
            list.add(new PhongHocModel("00"+i,"VIP",2));
        }
                return list;
    }

    public void ThemLTB(LoaiThietBiModel loaithietBi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1_MALOAI,loaithietBi.getMALOAI());
        values.put(COLUMN1_TENLOAI,loaithietBi.getTENLOAI());

        db.insert(LOAITHIETBI_TABLE,null,values);
    }

    public  void SuaLTB(LoaiThietBiModel loaithietBi)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1_MALOAI,loaithietBi.getMALOAI());
        values.put(COLUMN1_TENLOAI,loaithietBi.getTENLOAI());
        db.update(LOAITHIETBI_TABLE,values,"MALOAI ='"+loaithietBi.getMALOAI() +"'",null);
    }


    public  void XoaLTB(LoaiThietBiModel loaithietBi)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Delete from LOAITHIETBI where MALOAI= '"+loaithietBi.getMALOAI()+"'";
        db.execSQL(sql);

    }

    public ArrayList<LoaiThietBiModel> LayDLLTB()
    {
        ArrayList<LoaiThietBiModel> data = new ArrayList<>();
        String sql="select * from LOAITHIETBI";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do {
                LoaiThietBiModel loaithietBi = new LoaiThietBiModel();
                loaithietBi.setMALOAI(cursor.getString(0));
                loaithietBi.setTENLOAI(cursor.getString(1));
                data.add(loaithietBi);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}
        return  data;
    }
    public ArrayList<LoaiThietBiModel> LayDLLTB(String MALTB)
    {
        ArrayList<LoaiThietBiModel> data = new ArrayList<>();
        String sql="select * from LOAITHIETBI where MALOAI ='"+MALTB+"'";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do {
                LoaiThietBiModel loaithietBi = new LoaiThietBiModel();
                loaithietBi.setMALOAI(cursor.getString(0));
                loaithietBi.setTENLOAI(cursor.getString(1));
                data.add(loaithietBi);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}
        return  data;
    }
    public ArrayList<String> LayMaLoai() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from LOAITHIETBI ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }
        return data;
    }

    public void ThemTB(ThietBiModel thietbi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM3_MATB,thietbi.getMATB());
        values.put(COLUM3_TENTB,thietbi.getTENTB());
        values.put(COLUM3_XUATXU,thietbi.getXUATXU());
        values.put(COLUM3_MALOAI,thietbi.getMALOAI());

        db.insert(THIETBI_TABLE,null,values);
    }

    public  void SuaTB(ThietBiModel thietbi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM3_MATB,thietbi.getMATB());
        values.put(COLUM3_TENTB,thietbi.getTENTB());
        values.put(COLUM3_XUATXU,thietbi.getXUATXU());
        values.put(COLUM3_MALOAI,thietbi.getMALOAI());
        db.update(THIETBI_TABLE,values,"MATB ='"+thietbi.getMATB() +"'",null);
    }
    public  void XoaTB(ThietBiModel thietbi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Delete from THIETBI where MATB= '"+thietbi.getMATB()+"'";
        db.execSQL(sql);
    }
    public ArrayList<ThietBiModel> LayDLTB()
    {
        ArrayList<ThietBiModel> data = new ArrayList<>();
        String sql="select * from THIETBI";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do {
                ThietBiModel thietbi = new ThietBiModel();
                thietbi.setMATB(cursor.getString(0));
                thietbi.setTENTB(cursor.getString(1));
                thietbi.setXUATXU(cursor.getString(2));
                thietbi.setMALOAI(cursor.getString(3));
                data.add(thietbi);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}


        return  data;
    }

    public ArrayList<ThietBiModel> LayDLTB(String MATB)
    {
        ArrayList<ThietBiModel> data = new ArrayList<>();
        String sql="select * from THIETBI where MATB ='"+MATB+"'";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try{
            cursor.moveToFirst();
            do {
                ThietBiModel thietbi = new ThietBiModel();
                thietbi.setMATB(cursor.getString(0));
                thietbi.setTENTB(cursor.getString(1));
                thietbi.setXUATXU(cursor.getString(2));
                thietbi.setMALOAI(cursor.getString(3));
                data.add(thietbi);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}
        return  data;
    }
    public ArrayList<String> LayMaTB() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from THIETBI ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        } catch (Exception ex) { }
        return data;
    }

    public ArrayList<Top10Model> Top10(){
        ArrayList<Top10Model> list = new ArrayList<Top10Model>();
        String sql = "SELECT THIETBI.TENTB ,SUM(CHITIETSUDUNG.SOLUONG) AS TONGSL" +
                " FROM CHITIETSUDUNG INNER JOIN THIETBI ON THIETBI.MATB = CHITIETSUDUNG.MATB" +
                " GROUP BY CHITIETSUDUNG.MATB ORDER BY TONGSL DESC LIMIT 10";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            list.add(new Top10Model(cs.getString(0),cs.getInt(1) ));
            cs.moveToNext();
        }
        return list;
    }

    public void ThemPH(PhongHocModel phongHoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM2_MAPHONG,phongHoc.getMAPHONG());
        values.put(COLUM2_LOAIPHONG,phongHoc.getLOAIPHONG());
        values.put(COLUM2_TANG,phongHoc.getTANG());
        db.insert(PHONGHOC_TABLE,null,values);
    }

    public  void SuaPH(PhongHocModel phongHoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUM2_MAPHONG,phongHoc.getMAPHONG());
        values.put(COLUM2_LOAIPHONG,phongHoc.getLOAIPHONG());
        values.put(COLUM2_TANG,phongHoc.getTANG());
        db.update(PHONGHOC_TABLE,values,"MAPHONG ='"+phongHoc.getMAPHONG() +"'",null);
    }


    public  void XoaPH(PhongHocModel phongHoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql ="Delete from PhongHoc where MAPHONG= '"+phongHoc.getMAPHONG()+"'";
        db.execSQL(sql);

    }

    public ArrayList<PhongHocModel> LayDLPH()
    {
        ArrayList<PhongHocModel> data = new ArrayList<>();
        String sql="select * from PHONGHOC";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        try{
            cursor.moveToFirst();
            do {
                PhongHocModel phongHoc = new PhongHocModel();
                phongHoc.setMAPHONG(cursor.getString(0));
                phongHoc.setLOAIPHONG(cursor.getString(1));
                phongHoc.setTANG(cursor.getInt(2));
                data.add(phongHoc);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}
        return  data;
    }
    public ArrayList<PhongHocModel> LayDLPH(String maphong)
    {
        ArrayList<PhongHocModel> data = new ArrayList<>();
        String sql="select * from PHONGHOC where MAPHONG ='"+maphong+"'";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try{
            cursor.moveToFirst();
            do {
                PhongHocModel phongHoc = new PhongHocModel();
                phongHoc.setMAPHONG(cursor.getString(0));
                phongHoc.setLOAIPHONG(cursor.getString(1));
                phongHoc.setTANG(cursor.getInt(2));
                data.add(phongHoc);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex )
        {}



        return  data;
    }
    public ArrayList<String> LayMaPhong() {
        ArrayList<String> data = new ArrayList<>();
        String sql = "select * from PHONGHOC ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
        }
        return data;
    }

}
