package com.service.gimt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    private static int splashTimeOut=2000;

    RollSave rollSave;
    Cursor res;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo=(ImageView)findViewById(R.id.logo);


        rollSave = new RollSave(this);

        res = rollSave.getAllData();
        if(res.getCount() == 0){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, PreloginActivity.class);
                    startActivity(i);
                    finish();
                }
            } ,splashTimeOut);

        }else{

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, HomeActivityS.class);
                    startActivity(i);
                    finish();
                }
            } ,splashTimeOut);


        }


        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.anim_scale_up);
        logo.startAnimation(myanim);
    }
}
