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
import com.example.datlichkhambenh.model.Khoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListKhoaActivity extends AppCompatActivity {
    private DatabaseReference ref;
    private SharedPreferences prefs;
    String idBV;
    private Context mContext;
    private RecyclerView rcDatLich;
    private List<Khoa> mKhoa;
    private KhoaAdapter khoaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nd_ds_khoa);
        getSupportActionBar().hide();
        rcDatLich = findViewById(R.id.rcDatKham);

        getDataDoctor();
    }

    private void getDataDoctor() {
        idBV = getIntent().getStringExtra("IDBV");
        Log.d("testss", "idBV: " + idBV);
        mKhoa = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("dept");
        
        ref.orderByChild("idBV").equalTo(idBV).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mKhoa.clear();
            for (DataSnapshot ds : snapshot.getChildren()) {
                    Log.d("testss", "data: " + ds.toString());

                    Khoa khoa = ds.getValue(Khoa.class);
                    mKhoa.add(khoa);
                }
                    khoaAdapter = new KhoaAdapter(mKhoa);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rcDatLich.setLayoutManager(layoutManager);
                    rcDatLich.setAdapter(khoaAdapter);
                Log.d("testss", "list: " + mKhoa.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mKhoa.clear();
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    if(ds.hasChild(idBV)) {
//                        Khoa khoa = ds.getValue(Khoa.class);
//                        mKhoa.add(khoa);
//                    }
//                    Log.d("testss", "list: "+ mKhoa.size());
//
//
//                    khoaAdapter = new KhoaAdapter(mKhoa);
//                        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//                        rcDatLich.setLayoutManager(layoutManager);
//                        rcDatLich.setAdapter(khoaAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}