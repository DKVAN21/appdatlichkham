package com.example.datlichkhambenh;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datlichkhambenh.adapter.LichKhamAdapter;
import com.example.datlichkhambenh.model.PhieuKham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;



public class DatLichActivity extends AppCompatActivity {
    private EditText edNgay, edGio, edMota;
    private Button btnTaoPhieu, btnHuy;
    private DatabaseReference ref;
    private SharedPreferences prefs;
    private String idBs, idBn, tenBs, tenBn, timeLv, gioithieu, money, tiensu, mau, tuoi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_lich);
        getSupportActionBar().hide();
        setData();
        getData();
        mappingView();

        edNgay.setOnClickListener(v -> {
            showDateDialog();
        });
        edGio.setOnClickListener(v -> {
            showTimeDialog();
        });
        btnHuy.setOnClickListener(v -> {
            finish();
        });
        btnTaoPhieu.setOnClickListener(v -> {
            taoPhieuKham();
        });
    }

    private void setData() {
        idBs = getIntent().getStringExtra("IDBS");
        tenBs = getIntent().getStringExtra("TENBS");
        timeLv = getIntent().getStringExtra("TIMELV");
        gioithieu = getIntent().getStringExtra("INTRODUCE");
        money = getIntent().getStringExtra("MONEY");
        TextView tvTenBs = findViewById(R.id.tvTenBs_datLichNd);
        tvTenBs.setText("Bác sĩ: "+tenBs);
        TextView tvTimeLV = findViewById(R.id.tvTimeLVBs_datLichNd);
        tvTimeLV.setText("Thời gian làm việc: "+timeLv);
        TextView tvmoney = findViewById(R.id.tvMoneyBs_datLichNd);
        tvmoney.setText("Phí khám: "+money);
        TextView tvGioithieu = findViewById(R.id.tvGioithieuBs_datLichNd);
        tvGioithieu.setText("Giới thiệu: "+gioithieu);
    }

    private void getData() {
        prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        idBn = prefs.getString("USERNAME", "");
        tenBn = prefs.getString("FULLNAME", "");
        ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.child(idBn).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tiensu = dataSnapshot.child("tiensu").getValue(String.class);
                mau = dataSnapshot.child("mau").getValue(String.class);
                tuoi = dataSnapshot.child("age").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void mappingView() {
        edNgay = findViewById(R.id.edNgay_TaoPhieuKham);
        edGio = findViewById(R.id.edGio_TaoPhieuKham);
        edMota = findViewById(R.id.edMota);
        btnTaoPhieu = findViewById(R.id.btnTaoPhieuKham);
        btnHuy = findViewById(R.id.btnHuyPhieuKham);
    }

    private void showTimeDialog() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                edGio.setText(time);
            }
        };

        new TimePickerDialog(this, onTimeSetListener, hour, minute, true).show();
    }

    private void showDateDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                edNgay.setText(date);
            }
        };

        new DatePickerDialog(this, onDateSetListener, year, month, day).show();
    }

    private void taoPhieuKham() {
        Boolean checkError = true;
        String mota = edMota.getText().toString().trim();
        if(edNgay.getText().toString().trim().isEmpty()){
            edNgay.setError("Không được bỏ trống ngày");
            checkError = false;
        }
        if(edGio.getText().toString().trim().isEmpty()){
            edGio.setError("Không được bỏ trống thời gian");
            checkError = false;
        }
        if(checkError){
            ref = FirebaseDatabase.getInstance().getReference().child("History");
            PhieuKham phieuKham = new PhieuKham("null", "null", "null", "null", "null", "Đang chờ", "null", "null", "null", "null", "null","null","Chưa thanh toán","null");
            phieuKham.setId(ref.push().getKey());
            phieuKham.setIdBs(idBs);
            phieuKham.setTenBs(tenBs);
            phieuKham.setIdBn(idBn);
            phieuKham.setTenBn(tenBn);
            phieuKham.setNote(mota);
            phieuKham.setMoney(money);
            phieuKham.setTiensu(tiensu);
            phieuKham.setMau(mau);
            phieuKham.setTuoi(tuoi);
            phieuKham.setDate(edNgay.getText().toString().trim());
            phieuKham.setTime(edGio.getText().toString().trim());
            ref.child(phieuKham.getId()).setValue(phieuKham);
            Toast.makeText(DatLichActivity.this, "Đặt thành công", Toast.LENGTH_SHORT).show();
            Intent datlichIntent = new Intent(DatLichActivity.this, MainActivity.class);
            startActivity(datlichIntent);
        }
    }

}