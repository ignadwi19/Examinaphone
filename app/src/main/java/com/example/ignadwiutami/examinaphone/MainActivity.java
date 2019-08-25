package com.example.ignadwiutami.examinaphone;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_scan;
    Button btn_scheme;
    private static final int REQUEST_CAMERA = 1;
    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    public static final int RESULT_ENABLE = 11;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(flags);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //jika perrmision kamera belum aktif then ask jika sudah do nothing
        if (!(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            //respon permission akan dihandle pada onRequestPermissionResult() dgn overriding
        }

        btn_scheme = findViewById(R.id.btn_scheme);
        btn_scan = findViewById(R.id.btn_scan);
        btn_scheme.setOnClickListener(this);
        btn_scan.setOnClickListener(this);

    }


    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_scan:
                //jika permission granted langsung menuju scancode act
                //else req permission
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(getApplicationContext(), ScanCode.class));
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                }

                break;
            case R.id.btn_scheme:
                Intent scheme = new Intent(MainActivity.this, SkemaExam.class);
                startActivity(scheme);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
