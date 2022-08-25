package com.example.datlichkhambenh.fragment.nguoidung;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.adapter.LichSuNDAdapter;
import com.example.datlichkhambenh.model.PhieuKham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NdHistoryFragment extends Fragment {
    private EditText edFirstDate, edSecondDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private RecyclerView rcHistory;
    private List<PhieuKham> phieuKhamList;

    public NdHistoryFragment() {
        // Required empty public constructor
    }

    public static NdHistoryFragment newInstance() {
        NdHistoryFragment fragment = new NdHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView(inflater, container);
        edFirstDate = view.findViewById(R.id.edFirstDate);
        edSecondDate = view.findViewById(R.id.edSecondDate);
        rcHistory = view.findViewById(R.id.rcLichSuKhamNd);
        rcHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        getHistoryFromDb();
        return view;
    }

    private View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_nd_history, container, false);
        return view;
    }

    private void getHistoryFromDb() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String idNd = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("USERNAME", "none");
                    phieuKhamList = new ArrayList<>();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        if(ds.child("idBn").getValue(String.class).equalsIgnoreCase(idNd)) {
                            PhieuKham obj = ds.getValue(PhieuKham.class);
                            if(obj.getPay().equalsIgnoreCase("Chưa thanh toán")) {
                                phieuKhamList.add(obj);
                            }
                        }
                    }
                    LichSuNDAdapter lichSuNDAdapter = new LichSuNDAdapter(getContext(), phieuKhamList);
                    rcHistory.setAdapter(lichSuNDAdapter);
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