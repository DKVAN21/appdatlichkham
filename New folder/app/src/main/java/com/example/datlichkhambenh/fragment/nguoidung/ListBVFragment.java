package com.example.datlichkhambenh.fragment.nguoidung;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.adapter.BenhVienAdapter;
import com.example.datlichkhambenh.model.Benhvien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListBVFragment extends Fragment {
    private DatabaseReference ref;
    private Context mContext;
    private RecyclerView rcDatLich;
    private List<Benhvien> mBenhvien;
    private BenhVienAdapter BVAdapter;

    public ListBVFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static ListBVFragment newInstance() {
        ListBVFragment fragment = new ListBVFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nd_ds_benh_vien, container, false);
        rcDatLich = view.findViewById(R.id.rcDatKham);
        getDataDoctor();

        return view;
    }

    private void getDataDoctor() {
        mBenhvien = new ArrayList<>();
        mBenhvien.clear();
        ref = FirebaseDatabase.getInstance().getReference("hospitals");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mBenhvien.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(!ds.child("tenBV").getValue(String.class).isEmpty()) {
                        Benhvien benhvien = ds.getValue(Benhvien.class);
                        mBenhvien.add(benhvien);
                    }
                        BVAdapter = new BenhVienAdapter(mBenhvien);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        rcDatLich.setLayoutManager(layoutManager);
                        rcDatLich.setAdapter(BVAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}