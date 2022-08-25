package com.example.datlichkhambenh.fragment.bacsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.datlichkhambenh.BangTinActivity;
import com.example.datlichkhambenh.CapNhatThongTinBSActivity;
import com.example.datlichkhambenh.DangNhapActivity;
import com.example.datlichkhambenh.R;
import com.example.datlichkhambenh.VideocallActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BsHomeFragment extends Fragment implements View.OnClickListener {
    private Button btnLichKham;
    private CardView cvProfile, cvHistory, cvMessage, cvNews;
    private BottomNavigationView bnv;
    public BsHomeFragment() {
        // Required empty public constructor
    }

    public static BsHomeFragment newInstance() {
        BsHomeFragment fragment = new BsHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bs_home, container, false);
        bnv = getActivity().findViewById(R.id.bottom_nav);
        mapping(view);
        return view;
    }



    private void mapping(View view) {
        view.findViewById(R.id.cvProfile_bsHome).setOnClickListener(this);
        view.findViewById(R.id.cvHistory_bsHome).setOnClickListener(this);
        view.findViewById(R.id.cv_message_bsHome).setOnClickListener(this);
        view.findViewById(R.id.cv_news_bsHome).setOnClickListener(this);
        view.findViewById(R.id.cvPhieudat_bsHome).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.cvPhieudat_bsHome:
                bnv.setSelectedItemId(R.id.menu_history);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BsHistoryFragment()).commit();
                break;
            case R.id.cvHistory_bsHome:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BsHistoryFragment1()).commit();
                break;
            case R.id.cvProfile_bsHome:
                getActivity().startActivity(new Intent(getContext(), CapNhatThongTinBSActivity.class));
                break;
            case R.id.cv_message_bsHome:
                bnv.setSelectedItemId(R.id.menu_chat);
                getActivity().startActivity(new Intent(getContext(), VideocallActivity.class));
                break;
            case R.id.cv_news_bsHome:
                getActivity().startActivity(new Intent(getContext(), BangTinActivity.class));
                break;
        }
    }
}