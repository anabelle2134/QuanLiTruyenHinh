package com.example.theloai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TheLoaiDAO {
    public static int themTheLoai(TheLoai theLoai, SQLiteDatabase db){
        /**
         @param congTrinh: đối tượng cần thêm, not null
         @param db: writeable database instance từ lớp sqlhelper
         @return: 0 nếu thành công, -1 nếu thất bại
         */
        try {
            ContentValues values = new ContentValues();
            values.put(TheLoai.cotTenTheLoai, theLoai.getTenTheLoai());
            values.put(TheLoai.cotMoTa,theLoai.getMoTa());
            db.insert(TheLoai.tenBang, null, values);
            db.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    public static int xoaTheLoai(int maTheLoai, SQLiteDatabase db){
        /**
         @param maCongTrinh: maCongTrinh cần xoá
         @param db: writeable database từ lớp helper
         @return 0 nếu thành công, -1 nếu thất bại
         */
        try {
            String xoaQuery = String.format("DELETE from %s where %s='%s'",
                    TheLoai.tenBang,
                    TheLoai.cotmaTheLoai,
                    maTheLoai);
            db.execSQL(xoaQuery);
            db.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    public static int capTheLoai(TheLoai theLoai, SQLiteDatabase db){
        /**
         @param congTrinh: đối tượng công trình cần cập nhật
         @param db: writeable database từ lớp helper
         @return: 0 nếu thành công, -1 nếu thất bại
         */
        try {
            String capNhatQuery = String.format("UPDATE %s set %s = '%s',%s = '%s' where %s = '%s'",
                TheLoai.tenBang,
                  TheLoai.cotTenTheLoai,
                    theLoai.getTenTheLoai(),
                   TheLoai.cotMoTa,
                    theLoai.getMoTa(),
                 TheLoai.cotmaTheLoai,
                    theLoai.getMaTheLoai()
                    );
            db.execSQL(capNhatQuery);
            db.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    // không được sử dụng
    public static boolean kiemTraTonTaiTheLoai(String maTheLoai,SQLiteDatabase db){
//        String kiemTraQuery = "SELECT * FROM "+ CongTrinh.tenBang + " where maCongTrinh= '" + maCongTrinh +"'";
        String[] projection = {
                "maTheLoai"
        };
        String selection = "maTheLoai = ?";
        String[] selectionArgs = {maTheLoai};
        Cursor truyVanTheLoai = db.query(
               TheLoai.tenBang,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        db.close();
        return truyVanTheLoai.moveToNext();
    }

    /**
     * @param db database helper readable
     * @return danh sách công trình
     */
    public static ArrayList<TheLoai> layDanhSachTheLoai(SQLiteDatabase db){

        String[] projection = { // những cột muốn lấy
                TheLoai.cotmaTheLoai,
                TheLoai.cotTenTheLoai,
                TheLoai.cotMoTa
        } ;
        //selection = null
        Cursor cursor = db.query(
                TheLoai.tenBang,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<TheLoai> danhSachTheLoai = new ArrayList<>();
        while (cursor.moveToNext()){
            int mct = cursor.getInt(cursor.getColumnIndex(TheLoai.cotmaTheLoai));
            String tct = cursor.getString(cursor.getColumnIndex(TheLoai.cotTenTheLoai));
            String dcct = cursor.getString(cursor.getColumnIndex(TheLoai.cotMoTa));
          TheLoai temp = new TheLoai(tct,dcct);temp.setMaTheLoai(mct);
            danhSachTheLoai.add(temp);
        }
        return  danhSachTheLoai;
    }

    /**
     * @param db write able database
     * @param maTheLoai : int
     * @return CongTrinh <Object>
     */
    public static TheLoai timKiemTheoMaTheLoai(SQLiteDatabase db, int maTheLoai){
      TheLoai temp=null;
        String[] projection = { // những cột muốn lấy
                TheLoai.cotmaTheLoai,
                TheLoai.cotTenTheLoai,
                TheLoai.cotMoTa
//                CongTrinh.cotMaCongTrinh,
//                CongTrinh.cotTenCongTrinh,
//                CongTrinh.cotDiaChi
        } ;
        String selection = TheLoai.cotmaTheLoai+" = ?";
        String[] selectionArgs = {String.valueOf(maTheLoai)};
        Cursor cursor = db.query(
               TheLoai.tenBang,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToNext()) {
            int mct = cursor.getInt(cursor.getColumnIndex(TheLoai.cotmaTheLoai));
            String tct = cursor.getString(cursor.getColumnIndex(TheLoai.cotTenTheLoai));
            String dcct = cursor.getString(cursor.getColumnIndex(TheLoai.cotMoTa));
            temp = new TheLoai(tct, dcct);
            temp.setMaTheLoai(mct);
        }
        return temp;
    }
}
