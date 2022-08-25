package com.example.datlichkhambenh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HoadonActivity extends AppCompatActivity{

    Button btnThanhtoan, btnHuy;
    String id, tenbn, money, pay, tenbs;
    private TextView tvTenBN, tvPhikham, tvtenBs, tvpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        getSupportActionBar().hide();
        mappingView();
        setDATA();

        btnHuy.setOnClickListener(v -> {
            finish();
        });
    }


    private void setDATA(){
        id = getIntent().getStringExtra("ID");
        tenbn = getIntent().getStringExtra("TENBN");
        tenbs = getIntent().getStringExtra("TENBS");
        money = getIntent().getStringExtra("MONEY");
        pay = getIntent().getStringExtra("PAY");

        tvTenBN = findViewById(R.id.tvTenBn_thanhtoan);
        tvTenBN.setText(tenbn);
        tvPhikham = findViewById(R.id.tvPhikham_Thanhtoan);
        tvPhikham.setText(money);
        tvpay = findViewById(R.id.tvStatus_Thanhtoan);
        tvpay.setText(pay);
        tvtenBs = findViewById(R.id.tvBacsi_Thanhtoan);
        tvtenBs.setText(tenbs);
    }

    private void mappingView() {
        btnThanhtoan = findViewById(R.id.btnHoanThanhKham_thanhtoan);
        btnHuy = findViewById(R.id.btn_Huy_Thanhtoan);
    }
}