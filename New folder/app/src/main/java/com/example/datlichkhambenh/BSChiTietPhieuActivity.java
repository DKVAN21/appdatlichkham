package com.example.datlichkhambenh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.BStoathuocAdapter;
import com.example.datlichkhambenh.model.CTPhieuKham;
import com.example.datlichkhambenh.model.PhieuKham;
import com.example.datlichkhambenh.model.Toathuoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BSChiTietPhieuActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    public static final String IDBN = "IDBN";
    private RecyclerView rcThuoc;
    private List<Toathuoc> mThuoc;
    private BStoathuocAdapter thuocAdapter;
    private Context mContext;
    private EditText edBenh, edChidinh, edDando, edTenthuoc, edSL, edSongay;
    private Button btnLichsu, btnTaophieu, btnDong, btnKhamsau, btnThem;
    private String id, idBs, idBn, tenBn, note;
    private TextView tvTenBN, tvMota;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bs_chi_tiet_phieu);
        getSupportActionBar().hide();
        mappingView();
        setDATA();
        getDataThuoc();
        btnDong.setOnClickListener(v -> {
            finish();
        });
        btnLichsu.setOnClickListener(v -> {
        Intent datlichIntent = new Intent(BSChiTietPhieuActivity.this, LichsuBnActivity.class);
        datlichIntent.putExtra("IDBN", idBn);
        startActivity(datlichIntent);
        });
        btnTaophieu.setOnClickListener(v -> {
            taoPhieuKham();
            setStatusHT();
        });
        btnKhamsau.setOnClickListener(v -> {
            taoPhieuKham();
            setStatusCD();
        });
        btnThem.setOnClickListener(v -> {
           donthuoc();
            getDataThuoc();
        });
    }

    private void setDATA(){
        id = getIntent().getStringExtra("ID");
        tenBn = getIntent().getStringExtra("TENBN");
        idBn = getIntent().getStringExtra("IDBN");
        idBs = getIntent().getStringExtra("IDBS");
        note = getIntent().getStringExtra("NOTE");

        tvTenBN = findViewById(R.id.tvTenBn_datLichNd);
        tvTenBN.setText("Bệnh nhân: "+tenBn);
        tvMota = findViewById(R.id.tvMota_datLichNd);
        tvMota.setText("Mô tả: "+note);
    }

    private void mappingView() {
        rcThuoc = findViewById(R.id.rcToathuoc);
        btnLichsu = findViewById(R.id.btn_Lichsukham);
        btnKhamsau = findViewById(R.id.btnKhamsau);
        btnTaophieu = findViewById(R.id.btnTaoPhieuKham);
        btnDong = findViewById(R.id.btnHuyPhieuKham);
        btnThem = findViewById(R.id.btnThemTh);
        edBenh = findViewById(R.id.edBenh);
        edChidinh = findViewById(R.id.edChidinh);
        edDando = findViewById(R.id.edDando);
        edTenthuoc = findViewById(R.id.edTenthuoc);
        edSL = findViewById(R.id.edSoluong);
        edSongay = findViewById(R.id.edSongay);
    }

    private void taoPhieuKham() {
        Boolean checkError = true;

        String benh = edBenh.getText().toString().trim();
        String chidinh = edChidinh.getText().toString().trim();
        String dando = edDando.getText().toString().trim();

        if(edBenh.getText().toString().trim().isEmpty()){
            edBenh.setError("Không được bỏ trống!");
            checkError = false;
        }
        if(edDando.getText().toString().trim().isEmpty()){
            edDando.setError("Không được bỏ trống!");
            checkError = false;
        }
        if(checkError){
            ref = FirebaseDatabase.getInstance().getReference().child("CTPK");
            CTPhieuKham phieuKham = new CTPhieuKham("null", "null", "null", "null", "null", "null", "null", "null", "dd/mm/yyyy", "hh:mm");
            phieuKham.setId(id);
            phieuKham.setIdBn(idBn);
            phieuKham.setIdBs(idBs);
            phieuKham.setChidinh(chidinh);
            phieuKham.setBenh(dando);
            phieuKham.setBenh(benh);
            ref.child(phieuKham.getId()).setValue(phieuKham);
         //   finish();
            Intent datlichIntent = new Intent(BSChiTietPhieuActivity.this, MainActivity.class);
            startActivity(datlichIntent);
        }
        Toast.makeText(BSChiTietPhieuActivity.this, "Đã khám xong", Toast.LENGTH_SHORT).show();
    }

    private void setStatusHT() {
        ref = FirebaseDatabase.getInstance().getReference("History");
        ref.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PhieuKham phieuKham = new PhieuKham();
                phieuKham.setStatus("Hoàn thành");
                ref.child(phieuKham.getId()).setValue(phieuKham);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setStatusCD() {
        ref = FirebaseDatabase.getInstance().getReference("History");
        ref.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                PhieuKham phieuKham = new PhieuKham();
                phieuKham.setStatus("Khám sau");
                ref.child(phieuKham.getId()).setValue(phieuKham);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(BSChiTietPhieuActivity.this, "Đợi khám sau", Toast.LENGTH_SHORT).show();
    }

    private void donthuoc() {
        Boolean checkError = true;

        String tenthuoc = edTenthuoc.getText().toString().trim();
        String soluong = edSL.getText().toString().trim();
        String songay = edSongay.getText().toString().trim();

        if(edTenthuoc.getText().toString().trim().isEmpty()){
            edTenthuoc.setError("Không được bỏ trống!");
            checkError = false;
        }
        if(edSL.getText().toString().trim().isEmpty()){
            edSL.setError("Không được bỏ trống!");
            checkError = false;
        }
        if(edSongay.getText().toString().trim().isEmpty()){
            edSongay.setError("Không được bỏ trống!");
            checkError = false;
        }
        if(checkError){
            ref = FirebaseDatabase.getInstance().getReference("Toathuoc").child(id);
            Toathuoc thuoc = new Toathuoc();
            thuoc.setId(id);
            thuoc.setTenTh(tenthuoc);
            thuoc.setNgay(songay);
            thuoc.setsL(soluong);
            ref.child(thuoc.getTenTh()).setValue(thuoc);
        }
        Toast.makeText(BSChiTietPhieuActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
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
                    Toathuoc thuoc = ds.getValue(Toathuoc.class);
                    mThuoc.add(thuoc);
                    thuocAdapter = new BStoathuocAdapter(mThuoc);
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