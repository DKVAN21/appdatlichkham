package com.example.datlichkhambenh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datlichkhambenh.model.Users;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class DoimatkhauActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private  DatabaseReference ref;
    private TextInputLayout tilUsername, tilmkcu, tilmkmoi, tilremkmoi;
    private SharedPreferences prefs;

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        mappingView();
        prefs = getSharedPreferences("PREFS", MODE_MULTI_PROCESS);
        String getUsername = prefs.getString(USERNAME, "");
        ref = FirebaseDatabase.getInstance().getReference("users").child(getUsername);
        showAllUserData(getUsername);
        updateInfor();
    }

    private void showAllUserData(String username) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tilUsername.getEditText().setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateInfor() {
        findViewById(R.id.btn_ok_capNhatActivity).setOnClickListener(v -> {
            String userName = tilUsername.getEditText().toString().trim();
            String passcu = tilmkcu.getEditText().toString().trim();
            String passmoi = tilmkmoi.getEditText().toString().trim();
            String passremoi = tilremkmoi.getEditText().toString().trim();
            Boolean checkError = true;
            if(passcu.isEmpty()){
                tilmkmoi.setError("Mật khẩu cũ không được bỏ trống!");
                checkError = false;
            }
            if(passmoi.length()<6){
                tilmkmoi.setError("Mật khẩu phải từ 6 kí tự");
                checkError = false;
            }
            if(!passremoi.equals(passmoi)){
                tilremkmoi.setError("Nhập lại mật khẩu không đúng");
                checkError = false;
            }

            if(checkError) {
                Users user = new Users();
                user.setUserName(userName);
                user.setPassword(passmoi);
                database = FirebaseDatabase.getInstance();
                ref = database.getReference("users");
                ref.child(passcu).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(DoimatkhauActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            ref.child(userName).setValue(user);
                            Toast.makeText(DoimatkhauActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent dangkiIntent = new Intent(DoimatkhauActivity.this, MainActivity.class);
                startActivity(dangkiIntent);
            }
        });
    }

    private void mappingView() {
        tilUsername = findViewById(R.id.til_username_capNhatActivity);
        tilmkcu = findViewById(R.id.til_mkcu_doimkActivity);
        tilmkmoi = findViewById(R.id.til_mkmoi_doimkActivity);
        tilremkmoi = findViewById(R.id.til_remkmoi_doimkActivity);
    }

}