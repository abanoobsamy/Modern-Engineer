package com.eng_sherif.modern_engineering_industries.Ui.SignUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.paperdb.Paper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.HomeActivity;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.ChangeLanguageActivity;
import com.eng_sherif.modern_engineering_industries.Utils.LocaleHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    private static final String TAG = "SendOTPActivity";

    private Context mContext;

    private FirebaseAuth mAuth;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);

        mContext = SendOTPActivity.this;

//        requestSMSPermission();

        mAuth = FirebaseAuth.getInstance();

//        // Init paper first;
//        Paper.init(this);
//
//        // default language is English.
//        String language = Paper.book().read("language");
//        if (language == null)
//            Paper.book().write("language", "en");
//
//        updateView(Paper.book().read("language"));

        // set this to remove reCaptcha web
//        FirebaseAuth.getInstance().getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);

        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        setUpOTPAuthentication();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_lang, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//
//            case R.id.arabic:
//
//                Paper.book().write("language", "ar");
//                updateView(Paper.book().read("language"));
////                recreate();
//
//                ProcessPhoenix.triggerRebirth(SendOTPActivity.this);
//
//                break;
//
//            case R.id.english:
//
//                Paper.book().write("language", "en");
//                updateView(Paper.book().read("language"));
////                recreate();
//
//                ProcessPhoenix.triggerRebirth(SendOTPActivity.this);
//
//                break;
//        }
//
//        return false;
//    }

    private void updateView(String language) {

        Context context = LocaleHelper.setLocale(this, language);

        Resources resources = context.getResources();

//        btnAr.setText(resources.getString(R.string.Arabic));
//        btnEn.setText(resources.getString(R.string.English));
    }

    private void requestSMSPermission() {

        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);

        if (grant != PackageManager.PERMISSION_GRANTED) {

            String[] permissionList = new String[1];
            permissionList[0] = permission;

            ActivityCompat.requestPermissions(this, permissionList, 1);
        }
    }

    private void setUpOTPAuthentication() {

        Log.d(TAG, "setUpOTPAuthentication: ");

        //TODO: num1- we will check if number is already exist or no.

        EditText etNumber = findViewById(R.id.etInputMobileNumber);
        Button btnGet = findViewById(R.id.btnGetOTP);
        TextView tvError = findViewById(R.id.tvErrorSend);
        ProgressBar progressBar = findViewById(R.id.progressBar_Send);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: setUpOTPAuthentication");

                String inputNumber = etNumber.getText().toString().trim();

                char result = inputNumber.charAt(0);

                if (inputNumber.trim().isEmpty()) {

                    //that will still return until the user enter the number
                    Toast.makeText(mContext, "Enter Your Mobile!", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (result == '0') {

                    Toast.makeText(mContext, "Wrong number!", Toast.LENGTH_LONG).show();
                    return;
                }

                setUpPhoneAuthVerification(btnGet, progressBar, inputNumber, tvError);
            }
        });
    }

    private void setUpPhoneAuthVerification(Button btnGet, ProgressBar progressBar,
                                            String phoneNumber, TextView tvError) {

        progressBar.setVisibility(View.VISIBLE);
        btnGet.setVisibility(View.GONE);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);

                progressBar.setVisibility(View.GONE);
                btnGet.setVisibility(View.VISIBLE);

//                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Log.w(TAG, "onVerificationFailed"+ e.getMessage() + " : ", e);

                progressBar.setVisibility(View.GONE);
                btnGet.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.VISIBLE);

                tvError.setText(e.getMessage());

                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                Intent intent = new Intent(mContext, VerifyOTPActivity.class);
                intent.putExtra("mobile", phoneNumber);
                intent.putExtra("verificationId", mVerificationId);
                startActivity(intent);

                Toast.makeText(mContext, "OTP Send Successfully.", Toast.LENGTH_SHORT).show();
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+20" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void setUpStartActivity() {

        Intent intent = new Intent(mContext,
                HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

            setUpStartActivity();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}