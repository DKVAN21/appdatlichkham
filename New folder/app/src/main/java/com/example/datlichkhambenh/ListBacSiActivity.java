package com.example.datlichkhambenh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.BacSi1Adapter;
import com.example.datlichkhambenh.model.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListBacSiActivity extends AppCompatActivity {
    private DatabaseReference ref;
    private SharedPreferences prefs;
    String idBV, idKhoa;
    private Context mContext;
    private RecyclerView rcDatLich;
    private List<Doctor> mDoctors;
    private BacSi1Adapter BSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_nd_ds_bac_si);
        getSupportActionBar().hide();
        rcDatLich = findViewById(R.id.rcDatKham);

        getDataDoctor();
    }

    private void getDataDoctor() {
        idBV = getIntent().getStringExtra("IDBV");

        idKhoa = getIntent().getStringExtra("IDKHOA");
        Log.d("testss", "idKhoa: " + idKhoa);
        mDoctors = new ArrayList<>();
        mDoctors.clear();
        ref = FirebaseDatabase.getInstance().getReference("doctors");
        ref.orderByChild("idKhoa").equalTo(idKhoa).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mDoctors.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Log.d("testss", "data: " + ds.toString());
                            Doctor bacSi = ds.getValue(Doctor.class);
                            if (!bacSi.getFullName().isEmpty())
                                mDoctors.add(bacSi);
                }

                BSAdapter = new BacSi1Adapter(mDoctors);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rcDatLich.setLayoutManager(layoutManager);
                rcDatLich.setAdapter(BSAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}