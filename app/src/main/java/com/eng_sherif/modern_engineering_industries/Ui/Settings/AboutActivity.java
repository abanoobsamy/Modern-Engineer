package com.eng_sherif.modern_engineering_industries.Ui.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.eng_sherif.modern_engineering_industries.R;

public class AboutActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setUpToolbarAbout();

    }

    private void setUpToolbarAbout() {

        toolbar = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.about));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

}