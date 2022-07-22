package com.eng_sherif.modern_engineering_industries.Ui.SignUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Receiver.OTPReceiver;
import com.eng_sherif.modern_engineering_industries.Ui.HomeActivity;
import com.eng_sherif.modern_engineering_industries.Utils.FirebaseMethod;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;

public class VerifyOTPActivity extends AppCompatActivity {

    private static final String TAG = "SendOTPActivity";

    private Context mContext;

    private FirebaseMethod firebaseMethod;

    private Button btnVerify;
    private TextView tvMobile, tvResend;
    private String verificationId, mobile;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener stateListener;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private OTPReceiver otpReceiver;

    private EditText input1, input2, input3, input4, input5, input6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        mContext = VerifyOTPActivity.this;

        firebaseMethod = new FirebaseMethod(mContext);

        mAuth = FirebaseAuth.getInstance();

        verificationId = getIntent().getStringExtra("verificationId");
        mobile = getIntent().getStringExtra("mobile");

        setUpOTPInputs();

        // Get All Input fields Quickly.
        autoOTPReceiver();
    }

    private void initViews() {

        Log.d(TAG, "initViews: ");

        btnVerify = findViewById(R.id.btnVerify);
        tvMobile = findViewById(R.id.textMobile);
        tvResend = findViewById(R.id.tvResendOTP);

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setUpPhoneAuthVerification();

                clearFields();
            }
        });

        //inputsNumber
        input1 = findViewById(R.id.inputCode1);
        input2 = findViewById(R.id.inputCode2);
        input3 = findViewById(R.id.inputCode3);
        input4 = findViewById(R.id.inputCode4);
        input5 = findViewById(R.id.inputCode5);
        input6 = findViewById(R.id.inputCode6);

        //enter number
        tvMobile.setText(String.format("+20 - %s", mobile));

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (input1.getText().toString().trim().isEmpty()
                        || input2.getText().toString().trim().isEmpty()
                        || input3.getText().toString().trim().isEmpty()
                        || input4.getText().toString().trim().isEmpty()
                        || input5.getText().toString().trim().isEmpty()
                        || input6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(mContext, "Please enter valid code!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = input1.getText().toString() +
                        input2.getText().toString() +
                        input3.getText().toString() +
                        input4.getText().toString() +
                        input5.getText().toString() +
                        input6.getText().toString();

                if (verificationId != null) {

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    private void clearFields() {

        input1.setText("");
        input2.setText("");
        input3.setText("");
        input4.setText("");
        input5.setText("");
        input6.setText("");
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI

                            //TODO:
                            firebaseMethod.addNewUserInfo("", "0" + mobile);

                            Intent intent = new Intent(mContext, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                            Toast.makeText(mContext, "Verification Successfully.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                            Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setUpOTPInputs() {

        Log.d(TAG, "setUpOTPInputs: ");

        initViews();

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {

                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {

                    input3.requestFocus();
                }
                else {

                    input1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {

                    input4.requestFocus();
                }
                else {

                    input2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {

                    input5.requestFocus();
                }
                else {

                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {

                    input6.requestFocus();
                }
                else {

                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        input6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.toString().trim().isEmpty()) {

                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void autoOTPReceiver() {

        otpReceiver = new OTPReceiver();

        this.registerReceiver(otpReceiver, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));

        otpReceiver.initListener(new OTPReceiver.OTPReceiverListener() {
            @Override
            public void onOTPSuccess(String otp) {

                int o1 = Character.getNumericValue(otp.charAt(0));
                int o2 = Character.getNumericValue(otp.charAt(1));
                int o3 = Character.getNumericValue(otp.charAt(2));
                int o4 = Character.getNumericValue(otp.charAt(3));
                int o5 = Character.getNumericValue(otp.charAt(4));
                int o6 = Character.getNumericValue(otp.charAt(5));

                input1.setText(String.valueOf(o1));
                input2.setText(String.valueOf(o2));
                input3.setText(String.valueOf(o3));
                input4.setText(String.valueOf(o4));
                input5.setText(String.valueOf(o5));
                input6.setText(String.valueOf(o6));
            }

            @Override
            public void onOTPTimeout() {

                Toast.makeText(mContext, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpPhoneAuthVerification() {

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG, "onVerificationCompleted:" + credential);

//                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                Log.w(TAG, "onVerificationFailed"+ e.getMessage() + " : ", e);

                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                // Show a message and update the UI
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mResendToken = token;

                Toast.makeText(mContext, "Resend Code Successfully.", Toast.LENGTH_SHORT).show();
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+20" + mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (otpReceiver != null) {

            unregisterReceiver(otpReceiver);
        }
    }
}