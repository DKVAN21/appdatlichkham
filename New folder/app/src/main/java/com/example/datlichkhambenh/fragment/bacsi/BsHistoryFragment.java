package com.example.datlichkhambenh.fragment.bacsi;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.adapter.LichKhamAdapter;
import com.example.datlichkhambenh.model.PhieuKham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BsHistoryFragment extends Fragment {

    DatabaseReference ref;
    private EditText edFirstDate, edSecondDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private RecyclerView rcHistoryBs;
    private List<PhieuKham> phieuKhamList;
    private LichKhamAdapter lichkham;
    private Context mContext;
    private TextView txtDC, txtKS, txtHT;


    public static final String formatTime = "HH:mm";
    public static final String formatDate = "dd/MM/yyyy";

    public BsHistoryFragment() {
        // Required empty public constructor
    }


    public static BsHistoryFragment newInstance() {
        BsHistoryFragment fragment = new BsHistoryFragment();
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
        edFirstDate = view.findViewById(R.id.edFirstDate_bs);
        edSecondDate = view.findViewById(R.id.edSecondDate_bs);
        rcHistoryBs = view.findViewById(R.id.rcLichSuKhamBs);
        rcHistoryBs.setLayoutManager(new LinearLayoutManager(getContext()));
        getHistoryFromDb();
        edFirstDate.setOnClickListener(v -> {
            onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = dayOfMonth + "/" + month + "/" + year;
                    edFirstDate.setText(date);
                    getLichSu();
                }
            };
            chooseDate();

        });
        edSecondDate.setOnClickListener(v -> {
            onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;
                    String date = dayOfMonth + "/" + month + "/" + year;
                    edSecondDate.setText(date);
                    getLichSu();
                }
            };
            chooseDate();
        });
        return view;
    }


    private View getView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_bs_history, container, false);
        return view;
    }

    private void getLichSu() {
        if(edFirstDate.getText().toString().isEmpty() || edSecondDate.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Mốc thời gian bị trống!", Toast.LENGTH_SHORT).show();
        } else if(parseDate(edFirstDate.getText().toString().trim()).after(parseDate(edSecondDate.getText().toString().trim()))){
            Toast.makeText(getContext(), "Thời gian trước phải bé hơn thời gian sau", Toast.LENGTH_SHORT).show();
        } else {
            getHistoryLoc();
        }
    }

    private void getHistoryFromDb() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");
        databaseReference.orderByChild("date").startAt(currentDateandTime).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String idBs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("USERNAME", "none");
                    phieuKhamList = new ArrayList<>();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        if(ds.child("idBs").getValue(String.class).equalsIgnoreCase(idBs)) {
                            PhieuKham obj = ds.getValue(PhieuKham.class);
                            if(obj.getPay().equalsIgnoreCase("Đã thanh toán") && !obj.getStatus().equalsIgnoreCase("Hoàn thành")) {
                              phieuKhamList.add(obj);
                             }
                        }
                    }
                    LichKhamAdapter lichkhamAdapter = new LichKhamAdapter(getContext(), phieuKhamList);
                    rcHistoryBs.setAdapter(lichkhamAdapter);
                } catch (NullPointerException e){
                    Log.e("===//", ""+e);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getHistoryLoc() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String idBs = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE).getString("USERNAME", "none");
                    phieuKhamList = new ArrayList<>();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        if(ds.child("idBs").getValue(String.class).equalsIgnoreCase(idBs) && compareDate(parseDate(ds.child("date").getValue(String.class)))) {
                            PhieuKham obj = ds.getValue(PhieuKham.class);
                            phieuKhamList.add(obj);
                        }
                    }
                    LichKhamAdapter lichkhamAdapter = new LichKhamAdapter(getContext(), phieuKhamList);
                    rcHistoryBs.setAdapter(lichkhamAdapter);
                } catch (NullPointerException e){
                    Log.e("===//", ""+e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private Boolean compareDate(Date myDate){
        Date beforeDate = parseDate(edFirstDate.getText().toString().trim());
        Date afterDate = parseDate(edSecondDate.getText().toString().trim());

        if(beforeDate.compareTo(myDate) == 0 || beforeDate.before(myDate) && afterDate.after(myDate) || afterDate.compareTo(myDate) == 0){
            return true;
        }
        else return false;
    }

    private Date parseDate(String date){
        SimpleDateFormat inputParser = new SimpleDateFormat(formatDate);
        try {
            return inputParser.parse(date);
        } catch (ParseException e){
            return new Date(0);
        }
    }

    private void chooseDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                onDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}