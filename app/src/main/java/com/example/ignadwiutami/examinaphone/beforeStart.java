package com.example.ignadwiutami.examinaphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class beforeStart extends AppCompatActivity {

    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_start);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {

            Intent intent = getIntent();
            String url = intent.getStringExtra("url");

            @Override
            public void onClick(View view) {
                Intent startExam = new Intent(beforeStart.this, webView.class);
                startExam.putExtra("url", url);
                startActivity(startExam);
                finish();

            }
        });
    }
}
