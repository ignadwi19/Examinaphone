package com.example.ignadwiutami.examinaphone;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class webView extends AppCompatActivity {

    WebView brs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        brs = findViewById(R.id.webView);
        this.brs.setWebViewClient(new WebViewClient());
        this.brs.getSettings().setLoadsImagesAutomatically(true);
        this.brs.getSettings().setJavaScriptEnabled(true);
        this.brs.getSettings().setUseWideViewPort(true);
        this.brs.getSettings().setLoadWithOverviewMode(true);
        this.brs.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        this.brs.loadUrl(getIntent().getStringExtra("url"));
        startLockTask();
    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("PERHATIAN");
        builder.setMessage("jika anda keluar sebelum menyelesaikan ujian, data tidak akan tersimpan dan anda harus melakukan ujian dari awal. Pastikan ujian telah selesai disubmit. Anda yakin akan keluar?");

        builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                stopLockTask();
                Intent intent = new Intent(webView.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}