package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datlichkhambenh.model.Doctor;
import com.example.datlichkhambenh.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DangKiActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    DatabaseReference ref;
    Button btnDangKi, btnHuyBo;
    EditText etUserName, etPassword, etRePassword, etEmail;
    Spinner spinnerLever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        getSupportActionBar().hide();

        mappingView();
        setupSpinner();
        dangKi();
        btnHuyBo.setOnClickListener(v -> {
            startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));

            finish();
        });
    }

    private void dangKi() {
        btnDangKi.setOnClickListener(v -> {
            String userName = etUserName.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String rePass = etRePassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String level = spinnerLever.getSelectedItem().toString();

            Boolean checkError = true;
            if(userName.isEmpty()){
                etUserName.setError("Tên đăng nhập không được bỏ trống");
                checkError = false;
            }
            if(rePass.isEmpty()){
                etRePassword.setError("Nhập lại mật khẩu không được bỏ trống");
                checkError = false;
            }
            if(pass.length()<6){
                etPassword.setError("Mật khẩu phải từ 6 kí tự");
                checkError = false;
            }
            if(!rePass.equals(pass)){
                etRePassword.setError("Nhập lại mật khẩu không đúng");
                checkError = false;
            }
            if(!Pattern.matches("^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$", email)){
                etEmail.setError("Mail sai định dạng");
                checkError = false;
            }
            if(checkError){
                if(level.equals("Bệnh Nhân")) {
                Users user = new Users();
                user.setUserName(userName);
                user.setPassword(pass);
                user.setEmail(email);
                user.setLevel(level);
                user.setAge("");
                user.setBirthday("");
                user.setPhone("");
                user.setFullName("");
                user.setMau("");
                user.setTiensu("");
                database = FirebaseDatabase.getInstance();

                    ref = database.getReference("users");
                    ref.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(DangKiActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                            } else {
                                ref.child(userName).setValue(user);
                                Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                Intent dangkiIntent = new Intent(DangKiActivity.this, DangNhapActivity.class);
                dangkiIntent.putExtra("USERNAME",userName);
                dangkiIntent.putExtra("PASSWORD",pass);
                startActivity(dangkiIntent);
                }
                else{
                    Doctor doctor = new Doctor();
                    doctor.setUserName(userName);
                    doctor.setPassword(pass);
                    doctor.setEmail(email);
                    doctor.setLevel(level);
                    doctor.setIdBV("");
                    doctor.setIdKhoa("");
                    doctor.setFullName("");
                    doctor.setTimeLV("");
                    doctor.setIntroduce("");
                    doctor.setMoney("");
                    doctor.setStatus("");

                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference("doctors");
                    ref.child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(DangKiActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                            } else {
                                ref.child(userName).setValue(doctor);
                                Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                Intent dangkiIntent = new Intent(DangKiActivity.this, DangNhapActivity.class);
                dangkiIntent.putExtra("USERNAME",userName);
                dangkiIntent.putExtra("PASSWORD",pass);
                startActivity(dangkiIntent);
                }
            }
        });
    }

    private void setupSpinner() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Bệnh Nhân");
        arrayList.add("Bác Sĩ");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLever.setAdapter(arrayAdapter);
    }

    private void mappingView() {
        btnDangKi = findViewById(R.id.dangKi_btnDangKi);
        btnHuyBo = findViewById(R.id.dangKi_btnHuyBo);
        etUserName = findViewById(R.id.dangKi_edUserName);
        etPassword = findViewById(R.id.dangKi_etPassword);
        etRePassword = findViewById(R.id.dangKi_etRePassword);
        etEmail = findViewById(R.id.dangKi_etEmail);
        spinnerLever = findViewById(R.id.dangKi_spinnerLever);
    }
}