package com.example.datlichkhambenh;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.sinch.android.rtc.calling.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VideocallActivity extends BaseActivity {

    private TextView txtBN, txtBS;
    private Button btncall, btnstop;
    private String firebaseToken, idBN, idBS, tenBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocall);
        getSupportActionBar().hide();
        mappingview();
        tenBN = getIntent().getStringExtra("TENBN");
        txtBN.setText(idBN);
        txtBS.setText(idBS);
        btncall.setOnClickListener(buttonClickListener);
        btnstop.setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onServiceConnected() {
        TextView userName = (TextView) findViewById(R.id.txtBS);
        userName.setText(getSinchServiceInterface().getUserName());
        btncall.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        super.onDestroy();
    }

    //to kill the current session of SinchService
    private void stopButtonClicked() {
        if (getSinchServiceInterface() != null) {
            getSinchServiceInterface().stopClient();
        }
        finish();
    }

    //to place the call to the entered name
    private void callButtonClicked() {
        idBN = getIntent().getStringExtra("IDBN");
        if (idBN.isEmpty()) {
            Toast.makeText(this, "Please enter a user to call", Toast.LENGTH_LONG).show();
            return;
        }
        Call call = getSinchServiceInterface().callUserVideo(idBN);
        String callId = call.getCallId();

        Intent callScreen = new Intent(this, CallScreenActivity.class);
        callScreen.putExtra(SinchService.CALL_ID, callId);
        startActivity(callScreen);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCallvideo:
                    callButtonClicked();
                    break;

                case R.id.btnStop:
                    stopButtonClicked();
                    break;

            }
        }
    };

    private void mappingview() {
        txtBN = findViewById(R.id.txtBN);
        txtBS = findViewById(R.id.txtBS);
        btncall = findViewById(R.id.btnCallvideo);
        btnstop = findViewById(R.id.btnStop);
    }

}