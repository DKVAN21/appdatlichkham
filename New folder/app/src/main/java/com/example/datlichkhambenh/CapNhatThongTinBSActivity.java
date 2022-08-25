package com.example.datlichkhambenh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class CapNhatThongTinBSActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilHoTen, tilMail, tilidKhoa, tilidBV, tilTimeLV, tilIntroD, tilMoney;
    private SharedPreferences prefs;
    private DatabaseReference reference;

    public static final String IDKHOA = "IDKHOA";
    public static final String EMAIL = "EMAIL";
    public static final String FULLNAME = "FULLNAME";
    public static final String LEVEL = "LEVEL";
    public static final String IDBV = "idBV";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String TIMELV = "TimeLV";
    public static final String INTRODUCE = "introduce";
    public static final String MONEY = "money";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_thong_tin_bs);
        getSupportActionBar().setTitle("Cập nhật thông tin");
        mappingView();
        prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        String getUsername = prefs.getString(USERNAME, "");
        reference = FirebaseDatabase.getInstance().getReference("doctors").child(getUsername);
        showAllDoctorData(getUsername);
        updateInfor();
    }

    private void showAllDoctorData(String username) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String idKhoaFromDB = dataSnapshot.child("idKhoa").getValue(String.class);
                String emailFromDB = dataSnapshot.child("email").getValue(String.class);
                String fullNameFromDB = dataSnapshot.child("fullName").getValue(String.class);
                String idBVFromDB = dataSnapshot.child("idBV").getValue(String.class);
                String timeLVFromDB = dataSnapshot.child("timeLV").getValue(String.class);
                String introduceFromDB = dataSnapshot.child("introduce").getValue(String.class);
                String moneyFromDB = dataSnapshot.child("money").getValue(String.class);

                tilidKhoa.getEditText().setText(idKhoaFromDB);
                tilMail.getEditText().setText(emailFromDB);
                tilHoTen.getEditText().setText(fullNameFromDB);
                tilidBV.getEditText().setText(idBVFromDB);
                tilUsername.getEditText().setText(username);
                tilTimeLV.getEditText().setText(timeLVFromDB);
                tilIntroD.getEditText().setText(introduceFromDB);
                tilMoney.getEditText().setText(moneyFromDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void updateInfor() {
        findViewById(R.id.btn_ok_capNhatActivity).setOnClickListener(v -> {
            Boolean checkError = true;
            if(tilHoTen.getEditText().getText().toString().trim().isEmpty()){
                tilHoTen.setError("Tên không được để trống");
                checkError = false;
            }

            if(tilMail.getEditText().getText().toString().trim().isEmpty()){
                tilMail.setError("Email không được để trống");
                checkError = false;
            }

            if(tilidKhoa.getEditText().getText().toString().trim().isEmpty()){
                tilidKhoa.setError("Khoa không được để trống");
                checkError = false;
            }

            if(tilidBV.getEditText().getText().toString().trim().isEmpty()){
                tilidBV.setError("Bệnh viện không được để trống");
                checkError = false;
            }

            if(!Pattern.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$", tilMail.getEditText().getText().toString().trim())){
                tilMail.setError("Email sai định dạng");
                checkError = false;
            }

            if(tilTimeLV.getEditText().getText().toString().trim().isEmpty()){
                tilTimeLV.setError("Thời gian không được để trống");
                checkError = false;
            }

            if(tilIntroD.getEditText().getText().toString().trim().isEmpty()){
                tilIntroD.setError("Phần giới thiệu không được để trống");
                checkError = false;
            }

            if(tilMoney.getEditText().getText().toString().trim().isEmpty()){
                tilMoney.setError("Phí khám không được để trống");
                checkError = false;
            }

            if(checkError){
                reference.child("idKhoa").setValue(tilidKhoa.getEditText().getText().toString().trim());
                reference.child("email").setValue(tilMail.getEditText().getText().toString().trim());
                reference.child("fullName").setValue(tilHoTen.getEditText().getText().toString().trim());
                reference.child("idBV").setValue(tilidBV.getEditText().getText().toString().trim());
                reference.child("timeLV").setValue(tilTimeLV.getEditText().getText().toString().trim());
                reference.child("introduce").setValue(tilIntroD.getEditText().getText().toString().trim());
                reference.child("money").setValue(tilMoney.getEditText().getText().toString().trim());
                reference.child("status").setValue("Đang rảnh");
                prefs.edit().putString(FULLNAME, tilHoTen.getEditText().getText().toString().trim()).commit();
                startActivity(new Intent(CapNhatThongTinBSActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void mappingView() {
        tilUsername = findViewById(R.id.til_username_capNhatActivity);
        tilHoTen = findViewById(R.id.til_hoTen_capNhatActivity);
        tilMail = findViewById(R.id.til_mail_capNhatActivity);
        tilidKhoa = findViewById(R.id.til_idkhoa_capNhatActivity);
        tilidBV = findViewById(R.id.til_idbv_capNhatActivity);
        tilTimeLV = findViewById(R.id.til_timeLV_capNhatActivity);
        tilIntroD = findViewById(R.id.til_introd_capNhatActivity);
        tilMoney = findViewById(R.id.til_money_capNhatActivity);
    }

}