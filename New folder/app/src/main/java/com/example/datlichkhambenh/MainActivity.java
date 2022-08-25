package com.example.datlichkhambenh;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.datlichkhambenh.fragment.bacsi.BsHistoryFragment;
import com.example.datlichkhambenh.fragment.bacsi.BsHistoryFragment1;
import com.example.datlichkhambenh.fragment.bacsi.BsHomeFragment;
import com.example.datlichkhambenh.fragment.nguoidung.ListBVFragment;
import com.example.datlichkhambenh.fragment.nguoidung.NdChatFragment;
import com.example.datlichkhambenh.fragment.nguoidung.NdHistoryFragment;
import com.example.datlichkhambenh.fragment.nguoidung.NdHomeFragment;
import com.example.datlichkhambenh.fragment.nguoidung.NdSettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;
    public static final String FULLNAME = "FULLNAME";
    public static final String LEVEL = "LEVEL";

    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mappingView();
        prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
        String level = prefs.getString(LEVEL, "");
        String checkNameIsBlank = prefs.getString(FULLNAME, "");
/*
        if(checkNameIsBlank.isEmpty()) {
            Toast.makeText(this, "tên trống", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CapNhatThongTinActivity.class));
            finish();
        }

 */
        if(level.equals("Bệnh Nhân")){
            addViewUsers();
        }
        else {
            addViewDoctors();
        }
    }

    private void addViewDoctors() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new BsHomeFragment()).commit();

        bnv.setOnItemSelectedListener(item -> {
            Fragment currentFragment = null;
            switch (item.getItemId()){
                case R.id.menu_home:
                    currentFragment = new BsHomeFragment();
                    break;
                case R.id.menu_history:
                    currentFragment = new BsHistoryFragment1();
                    break;
                case R.id.menu_datLich:
                    currentFragment = new BsHistoryFragment();
                    break;
                case R.id.menu_chat:
                    currentFragment = new NdChatFragment();
                    break;
                case R.id.menu_setting:
                    currentFragment = new NdSettingFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, currentFragment).commit();
            return true;
        });
    }

    private void addViewUsers() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new NdHomeFragment()).commit();

        bnv.setOnItemSelectedListener(item -> {
            Fragment currentFragment = null;
            switch (item.getItemId()){
                case R.id.menu_home:
                    currentFragment = new NdHomeFragment();
                    break;
                case R.id.menu_history:
                    currentFragment = new NdHistoryFragment();
                    break;
                case R.id.menu_datLich:
                    currentFragment = new ListBVFragment();
                    break;
                case R.id.menu_chat:
                    currentFragment = new NdChatFragment();
                    break;
                case R.id.menu_setting:
                    currentFragment = new NdSettingFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, currentFragment).commit();
            return true;
        });
    }

    private void mappingView() {
        bnv = findViewById(R.id.bottom_nav);
    }
}