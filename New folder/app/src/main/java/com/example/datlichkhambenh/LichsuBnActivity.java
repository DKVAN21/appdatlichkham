package com.example.datlichkhambenh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.KhoaAdapter;
import com.example.datlichkhambenh.adapter.LichSuNDAdapter;
import com.example.datlichkhambenh.adapter.LichSuNDinBSAdapter;
import com.example.datlichkhambenh.model.Khoa;
import com.example.datlichkhambenh.model.PhieuKham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LichsuBnActivity extends AppCompatActivity {
    String idBn;
    private Context mContext;
    private RecyclerView rcDatLich;
    private List<PhieuKham> phieuKhamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bs_ls_nd);
        getSupportActionBar().setTitle("Lịch sử khám bệnh nhân");
        getDataDoctor();
    }

    private void getDataDoctor() {
        rcDatLich = findViewById(R.id.rcLichSuKhamBn);
        idBn = getIntent().getStringExtra("IDBN");
        Log.d("testss", "idBn: " + idBn);
        phieuKhamList = new ArrayList<>();
        phieuKhamList.clear();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                phieuKhamList.clear();
                try {
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        if (ds.child("idBn").getValue(String.class).equals(idBn)) {
                            PhieuKham obj = ds.getValue(PhieuKham.class);
                            if (!obj.getStatus().equalsIgnoreCase("Đang chờ")) {
                                phieuKhamList.add(obj);
                            }
                        }
                    }
                    LichSuNDinBSAdapter lichSuNDAdapter = new LichSuNDinBSAdapter(mContext,phieuKhamList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rcDatLich.setLayoutManager(layoutManager);
                    rcDatLich.setAdapter(lichSuNDAdapter);
                } catch (NullPointerException e){
                    Log.e("===//", ""+e);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}