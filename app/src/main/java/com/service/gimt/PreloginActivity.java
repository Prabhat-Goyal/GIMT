package com.service.gimt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PreloginActivity extends AppCompatActivity {
    private Button button_1;
    private Button button_2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login_new);

        initComponent();

    }

    private void initComponent (){
        button_1= findViewById(R.id.tb);
        button_2= findViewById(R.id.sb);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PreloginActivity.this,ActivityLoginT.class);
                startActivity(intent);
                finish();
            }

        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PreloginActivity.this, ActivityLoginS.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
