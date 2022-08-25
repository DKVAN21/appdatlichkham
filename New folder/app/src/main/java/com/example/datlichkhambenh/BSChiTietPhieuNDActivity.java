package com.example.datlichkhambenh;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.NDtoathuocAdapter;
import com.example.datlichkhambenh.model.PhieuKham;
import com.example.datlichkhambenh.model.Toathuoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BSChiTietPhieuNDActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView rcThuoc;
    private List<Toathuoc> mThuoc;
    private NDtoathuocAdapter thuocAdapter;
    private Button btnDong;
    String id, tenBs, money, status, pay;
    private TextView tvTenBs, tvStatus, tvMoney, tvdando, tvbenh, tvchidinh, tvdonthuoc;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bs_chi_tiet_phieu_nd);
        getSupportActionBar().hide();
        setDATA();
        getData();
        getDataThuoc();
        btnDong.setOnClickListener(v -> {
            finish();
        });

    }
    private void setDATA(){
        id = getIntent().getStringExtra("ID");
        tenBs = getIntent().getStringExtra("TENBS");
        money = getIntent().getStringExtra("MONEY");
        status = getIntent().getStringExtra("STATUS");

        rcThuoc = findViewById(R.id.rcToathuoc);
        btnDong = findViewById(R.id.btnDong_ChiTietPhieund);
        tvbenh = findViewById(R.id.tv_chuandoan);
        tvchidinh = findViewById(R.id.tv_chidinh);
        tvdando = findViewById(R.id.tv_Dando);
        tvTenBs = findViewById(R.id.tvTenBs_datLichNd);
        tvTenBs.setText("Bác sĩ: "+tenBs);
        tvStatus = findViewById(R.id.tvStatus_chitietNd);
        tvStatus.setText("Trạng thái: "+status);
        tvMoney = findViewById(R.id.tvMoneyBs_datLichNd);
        tvMoney.setText("Phí khám: "+money);
    }


    private void getData() {
        ref = FirebaseDatabase.getInstance().getReference().child("CTPK");
        ref.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    String chuandoan = data.child("benh").getValue(String.class);
                    String chidinh = data.child("chidinh").getValue(String.class);
                    String dando = data.child("dando").getValue(String.class);

                    tvbenh.setText(chuandoan);
                    tvchidinh.setText(chidinh);
                    tvdando.setText(dando);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getDataThuoc() {
        mThuoc = new ArrayList<>();
        mThuoc.clear();
        ref = FirebaseDatabase.getInstance().getReference("Toathuoc").child(id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mThuoc.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (!ds.child("tenTh").getValue(String.class).isEmpty()) {
                        Toathuoc thuoc = ds.getValue(Toathuoc.class);
                        mThuoc.add(thuoc);
                    }
                    thuocAdapter = new NDtoathuocAdapter(mThuoc);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rcThuoc.setLayoutManager(layoutManager);
                    rcThuoc.setAdapter(thuocAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}