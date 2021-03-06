package com.example.theloai;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietTheLoai extends AppCompatActivity {
    EditText maCongTrinhEdt;
    EditText tenCongTrinhEdt;
    EditText diaChiCongTrinhEdt;
    ImageButton suaChiTietBtn;
    ImageButton inChiTietBtn;
    int maCongTrinhIntent;
    CSDLTruyenHinh database;
    TheLoai theLoai; // cong trinh đang load
    //ListView danhSachPhieuVanChuyenLv;
   // ArrayList<PhieuVanChuyen> data; // phiếu vận chuyển thuộc công trình
  //  PhieuVanChuyenAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_the_loai);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi tiết công trình");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent homeIntent = getIntent();
        maCongTrinhIntent = homeIntent.getIntExtra("maCongTrinh",0);
        database = new CSDLTruyenHinh(this);
       // data = loadDanhSachPhieuVanChuyen();
       theLoai = getCongTrinhFromDatabase(database);
        setControl();
        setEvent(); // load data trước khi gọi event
    }

    /** lấy công trình thông qua maCongTrinh
     * @param database
     * @return congTrinh
     */
    private TheLoai getCongTrinhFromDatabase(CSDLTruyenHinh database) {
        return TheLoaiDAO.timKiemTheoMaTheLoai(database.getReadableDatabase(),maCongTrinhIntent);
    }
//    private ArrayList<PhieuVanChuyen> loadDanhSachPhieuVanChuyen(){
//        return PhieuVanChuyenDAO.danhSachPhieuVanChuyenTheoCongTrinh(database.getReadableDatabase(),maCongTrinhIntent);
//    }

    private void setControl() {
        maCongTrinhEdt = (EditText) findViewById(R.id.maCongTrinhEdt_CTCT);
        tenCongTrinhEdt = findViewById(R.id.tenCongTrinhEdt_CTCT);
        diaChiCongTrinhEdt = findViewById(R.id.diaChiCongTrinhEdt_CTCT);
        suaChiTietBtn = findViewById(R.id.suaChiTietCongTrinhBtn_CTCT);
        inChiTietBtn = findViewById(R.id.inChiTietCongTrinhBtn_CTCT);
       // danhSachPhieuVanChuyenLv = findViewById(R.id.danhSachPhieuVanChuyenLv_CTCT);
    }
    private void setEvent() {
        // set data cho các editText hiển thị công trình đang chọn
        maCongTrinhEdt.setText(String.valueOf(theLoai.getMaTheLoai()));
        tenCongTrinhEdt.setText(theLoai.getTenTheLoai());
        diaChiCongTrinhEdt.setText(theLoai.getMoTa());
        // set listview adapter để hiển thị danh sách công trình
//        adapter = new PhieuVanChuyenAdapter(this,R.layout.danh_sach_phieu_van_chuyen_custom_listview,data);
//        adapter.setDb(database); // set database cho adapter
//        danhSachPhieuVanChuyenLv.setAdapter(adapter);
        // set listener cho các btn để thực hiện chức năng
        suaChiTietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suaChiTietBtn.getTag().toString().equals("non-edit")) {
                    tenCongTrinhEdt.setEnabled(true);
                    diaChiCongTrinhEdt.setEnabled(true);
                    suaChiTietBtn.setImageResource(R.drawable.done_edit_vector);
                    suaChiTietBtn.setTag("editing");
                }else{
                    tenCongTrinhEdt.setEnabled(false);
                    diaChiCongTrinhEdt.setEnabled(false);
                    suaChiTietBtn.setImageResource(R.drawable.edit_vector);
                    theLoai.setMoTa(diaChiCongTrinhEdt.getText().toString().trim());
                 theLoai.setTenTheLoai(tenCongTrinhEdt.getText().toString().trim());
                   TheLoaiDAO.capTheLoai(theLoai,database.getWritableDatabase());
                    suaChiTietBtn.setTag("non-edit");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_capnhat_timkiem_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.timKiem_ATB:
               // timPhieuVanChuyen();
                break;
            case R.id.them_ATB:
              //  themPhieuVanChuyen();
                lamMoi();
                break;
            case R.id.lamMoi_ATB:
                lamMoi();
                Toast.makeText(this, "Làm mới thành công!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lamMoi() {
      //  data = loadDanhSachPhieuVanChuyen();
      //  adapter.data = data;
      //  adapter.notifyDataSetChanged();
    }
//    private int timPhieuVanChuyen() {
//        try {
//            LayoutInflater layoutInflater = LayoutInflater.from(this);
//            View timPhieuVanChuyenDialog = layoutInflater.inflate(R.layout.tim_kiem_dialog, null); // tìm dialog view layout từ inflater
//            MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this); // tạo dialog builder : lớp hỗ trợ xây dựng dialog
//            alertDialogBuilder.setView(timPhieuVanChuyenDialog); // set view tìm được cho dialog
//            EditText ngayCanTimEdt = (EditText) timPhieuVanChuyenDialog.findViewById(R.id.timKiemEdt_dialog); // lấy control các trường đã tạo trên dialog
//            ngayCanTimEdt.setText("Nhập ngày cần tìm: dd/mm/yyyy");
//            alertDialogBuilder
//                    .setCancelable(false)
//                    .setPositiveButton("Tìm", // cài đặt nút đồng ý hành động
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    SQLiteDatabase db = database.getWritableDatabase();
//                                    String ngayCanTim = ngayCanTimEdt.getText().toString().trim();
//                                    if(ngayCanTim.isEmpty()==false) {
//                                        int tempSize = adapter.getCount();
////                                        Log.d("find", "có "+String.valueOf(tempSize) + " phân từ");
////                                        Log.d("find 1", ""+String.valueOf(data.get(0).getMaPhieuVanChuyen()) + " phân từ");
////                                        data.remove(0);
////                                        adapter.notifyDataSetChanged();
////                                        Log.d("find 2", ""+String.valueOf(data.get(0).getMaPhieuVanChuyen()) + " phân từ");
//
//                                        for(int i=0;i<data.size();i++){
//                                            if(data.get(i).getNgayVanChuyen().equals(ngayCanTim)==false){
//                                                System.out.println("mã bị xoá: " + data.get(i).getMaPhieuVanChuyen());
//                                                data.remove(i);
//                                                adapter.notifyDataSetChanged();
//                                                i--;
//                                            }
//                                        }
//
//                                    }
//                                }
//                            })
//                    .setNegativeButton("Huỷ", // cài đặt nút huỷ hành động
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create(); // tạo dialog từ dialog builder
//            alertDialog.show();//show diaglo
//            return 0;
//        } catch (Exception e) {
//            return -1;
//        }
//    }

//    private void themPhieuVanChuyen() {
//        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
//        Date date = new Date();
//        String ngayVanChuyen = simpleDate.format(date);
//        PhieuVanChuyen phieuVanChuyen = new PhieuVanChuyen(ngayVanChuyen,maCongTrinhIntent);
//        if(PhieuVanChuyenDAO.themPhieuVanChuyen(phieuVanChuyen,database.getWritableDatabase())==0){
//            data.add(phieuVanChuyen);
//            adapter.notifyDataSetChanged();
//            Toast.makeText(this, "Thêm phiếu thành công! Hãy bấm làm mới!", Toast.LENGTH_SHORT).show();
//        }
//    }

}