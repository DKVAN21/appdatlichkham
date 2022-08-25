package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class ThanhtoanActivity extends AppCompatActivity{

    Button btnThanhtoan, btnHuy;
    String id, tenbn, money, pay;
    private TextView tvTenBN, tvPhikham, tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        getSupportActionBar().hide();
        mappingView();
        setDATA();

        btnHuy.setOnClickListener(v -> {
            finish();
        });
        btnThanhtoan.setOnClickListener(v -> {
        });
    }


    private void setDATA(){
        id = getIntent().getStringExtra("ID");
        tenbn = getIntent().getStringExtra("TENBN");
        money = getIntent().getStringExtra("MONEY");
        pay = getIntent().getStringExtra("PAY");

        tvTenBN = findViewById(R.id.tvTenBn_thanhtoan);
        tvTenBN.setText(tenbn);
        tvPhikham = findViewById(R.id.tvPhikham_Thanhtoan);
        tvPhikham.setText(money);

    }

    private void mappingView() {
        btnThanhtoan = findViewById(R.id.btnHoanThanhKham_thanhtoan);
        btnHuy = findViewById(R.id.btn_Huy_Thanhtoan);
        tvMessage = findViewById(R.id.tv_Message);
    }



}