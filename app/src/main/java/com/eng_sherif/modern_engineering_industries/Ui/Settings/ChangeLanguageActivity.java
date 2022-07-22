package com.eng_sherif.modern_engineering_industries.Ui.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.paperdb.Paper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.Fragments.MenuFragment;
import com.eng_sherif.modern_engineering_industries.Ui.HomeActivity;
import com.eng_sherif.modern_engineering_industries.Ui.SignUser.SendOTPActivity;
import com.eng_sherif.modern_engineering_industries.Utils.LocaleHelper;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.util.Locale;

public class ChangeLanguageActivity extends AppCompatActivity {

    Toolbar toolbar;

    Button btnEn, btnAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        setUpToolbarLanguage();

        // Init paper first;
        Paper.init(this);

        // default language is English.
        String language = Paper.book().read("language");

        if (language == null) {
            Paper.book().write("language", "en");
        }

        updateView(Paper.book().read("language"));

        btnAr = findViewById(R.id.BTN_ar);
        btnEn = findViewById(R.id.BTN_en);

        btnAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                setLocale("ar");

                Paper.book().write("language", "ar");
                updateView(Paper.book().read("language"));
                recreate();
                startActivity(new Intent(ChangeLanguageActivity.this, HomeActivity.class));
                finish();
//                ProcessPhoenix.triggerRebirth(ChangeLanguageActivity.this);
            }
        });

        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                setLocale("en");

                Paper.book().write("language", "en");
                updateView(Paper.book().read("language"));
                recreate();
                startActivity(new Intent(ChangeLanguageActivity.this, HomeActivity.class));
                finish();
//                ProcessPhoenix.triggerRebirth(ChangeLanguageActivity.this);
            }
        });
    }

    private void updateView(String language) {

        Context context = LocaleHelper.setLocale(this, language);

        Resources resources = context.getResources();

//        btnAr.setText(resources.getString(R.string.Arabic));
//        btnEn.setText(resources.getString(R.string.English));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    private void setUpToolbarLanguage() {

        toolbar = findViewById(R.id.toolbarLanguage);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.language));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setLocale(String language) {

        Locale myLocale = new Locale(language);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();

        conf.locale = myLocale;

        getResources().updateConfiguration(conf, dm);

        recreate();
    }

/*         "en" = English
            "hi" =Hindi
            "fr" =French
            "it" =Italian
            "de" =German
            "es" =Spanish
            "ja" =Japanese
            "ko" =Korean
            "nl" =Dutch
            "pt" =Portuguese
            "ru" =Russian
            "zh" =Chinese
            "ar" = arabic

*/

}