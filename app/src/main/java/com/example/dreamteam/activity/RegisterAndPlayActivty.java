package com.example.dreamteam.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dreamteam.R;

public class RegisterAndPlayActivty extends AppCompatActivity implements View.OnClickListener {
    private TextView tvRegistration,tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_and_play_activty);
        init();

        onClick();

    }

    private void onClick() {
        tvLogin.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
    }

    private void setLogInFragment(boolean isLogin) {
        startActivity(new Intent(this,RegisterLoginActivty.class)
        .putExtra("isLogin",isLogin));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        tvLogin = findViewById(R.id.tvLogin);
        tvRegistration = findViewById(R.id.tvRegistration);
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id == tvRegistration.getId()){
            setLogInFragment(false);
        }else if (id == tvLogin.getId()){
            setLogInFragment(true);
        }
    }
}
