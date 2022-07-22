package com.eng_sherif.modern_engineering_industries.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.SignUser.SendOTPActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_home);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenHomeActivity.this, SendOTPActivity.class));
                finish();
            }
        }, 5000);
    }
}