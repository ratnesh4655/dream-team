package com.example.dreamteam.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dreamteam.R;
import com.example.dreamteam.common.Utils;

public class SplaceActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setTranslateStatusBar(this);

        Handler mSplashHandler = new Handler();
        Runnable action = new Runnable() {
            @Override
            public void run() {
              startActivity(new Intent(SplaceActivty.this,RegisterAndPlayActivty.class));
              finish();
            }
        };
        mSplashHandler.postDelayed(action, 1000);
    }
}
