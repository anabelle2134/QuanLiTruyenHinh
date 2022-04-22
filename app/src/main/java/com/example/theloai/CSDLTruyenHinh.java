package com.example.theloai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CSDLTruyenHinh extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "QuanLyVanChuyen.db";

    public CSDLTruyenHinh(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String theLoai = String.format("CREATE TABLE %s(%s integer primary key autoincrement,%s TEXT,%s TEXT)",
              TheLoai.tenBang,
                TheLoai.cotmaTheLoai,
                TheLoai.cotTenTheLoai,
                TheLoai.cotMoTa);
//                CongTrinh.cotMaCongTrinh,
//                CongTrinh.cotTenCongTrinh,
//                CongTrinh.cotDiaChi);
//        String phieuVanChuyen = String.format("CREATE TABLE %s(%s integer primary key autoincrement,%s integer,%s TEXT)",
//                PhieuVanChuyen.tenBang,
//                PhieuVanChuyen.cotMaPhieuVanChuyen,
//                PhieuVanChuyen.cotMaCongTrinh,
//                PhieuVanChuyen.cotNgayVanChuyen);
//        //CTPVC có trường ID để dễ dàng xoá, cập nhật
//        String chiTietPhieuVanChuyen = String.format("CREATE TABLE %s(id integer primary key autoincrement not null,%s Integer,%s TEXT,%s INTEGER,%s INTEGER)",
//                ChiTietPhieuVanChuyen.tenBang,
//                ChiTietPhieuVanChuyen.cotMaPhieuVanChuyen,
//                ChiTietPhieuVanChuyen.cotMaVatTu,
//                ChiTietPhieuVanChuyen.cotSoLuong,
//                ChiTietPhieuVanChuyen.cotCuLy);
//        String vatTu = String.format("CREATE TABLE %s(%s TEXT primary key,%s TEXT,%s TEXT,%s INTEGER, %s BLOB)",
//                VatTu.tenBang,
//                VatTu.cotMaVatTu,
//                VatTu.cotTenVatTu,
//                VatTu.cotDonViTinh,
//                VatTu.cotGia,
//                VatTu.cotAnh);
//        String loaiDonViTinh = String.format("CREATE TABLE %s(%s INTEGER primary key autoincrement,%s TEXT)",
//                LoaiDonViTinh.tenBang,
//                LoaiDonViTinh.cotMaDonViTinh,
//                LoaiDonViTinh.cotTenDonViTinh);
        db.execSQL(theLoai);
//        db.execSQL(phieuVanChuyen);
//        db.execSQL(chiTietPhieuVanChuyen);
//        db.execSQL(vatTu);
//        db.execSQL(loaiDonViTinh);
        Log.d("1111", "onCreate: khoi tao csdl");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_upgrade = "DROP TABLE IF EXISTS ";
        db.execSQL(sql_upgrade + TheLoai.tenBang);
//        db.execSQL(sql_upgrade+PhieuVanChuyen.tenBang);
//        db.execSQL(sql_upgrade+CongTrinh.tenBang);
//        db.execSQL(sql_upgrade+ChiTietPhieuVanChuyen.tenBang);
        onCreate(db);
    }
}
