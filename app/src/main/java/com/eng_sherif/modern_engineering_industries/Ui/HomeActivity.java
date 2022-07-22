package com.eng_sherif.modern_engineering_industries.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.Fragments.HomeFragment;
import com.eng_sherif.modern_engineering_industries.Ui.Fragments.MenuFragment;
import com.eng_sherif.modern_engineering_industries.Ui.SignUser.SendOTPActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ChipNavigationBar bottomNavigationView;

    private HomeFragment homeFragment = new HomeFragment();
    private MenuFragment menuFragment = new MenuFragment();

    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpFirebaseAuthentication();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setUpToolbarHome();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                switch (i) {

                    case R.id.home:

//                        toolbar.setBackgroundColor(getColor(R.color.colorPrimary));
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        break;

                    case R.id.menu:

//                        toolbar.setBackgroundColor(getColor(R.color.green));
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
                        break;
                }
            }
        });
    }

    private void setUpToolbarHome() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void checkCurrentUser(FirebaseUser user) {

        if (user == null) {

            Intent intent = new Intent(this, SendOTPActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void setUpFirebaseAuthentication() {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser fUser = mAuth.getCurrentUser();

                if (fUser != null) {

                    Log.d(TAG, "onAuthStateChanged: SignIn" + fUser.getUid());
                }
                else {

                    Log.d(TAG, "onAuthStateChanged: SignOut");

                    //check if the user logged in
                    checkCurrentUser(fUser);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(stateListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mAuth != null) {

            mAuth.removeAuthStateListener(stateListener);
        }
    }

}
