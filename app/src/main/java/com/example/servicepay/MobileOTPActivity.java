package com.example.servicepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileOTPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_otpactivity);
        final EditText mobileinput = findViewById(R.id.mobilenum);
        final Button mobilebtn = findViewById(R.id.btnmobile);
        final ProgressBar progressBar = findViewById(R.id.progressbar);



        mobilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobileinput.getText().toString().trim().isEmpty()){
                    Toast.makeText(MobileOTPActivity.this, "Please enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mobilebtn.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+63" + mobileinput.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        MobileOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                mobilebtn.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                mobilebtn.setVisibility(View.VISIBLE);
                                Toast.makeText(MobileOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String idVerification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                mobilebtn.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                intent.putExtra("mobile", mobileinput.getText().toString());
                                intent.putExtra("idVerification", idVerification);
                                startActivity(intent);
                            }
                        }
                );

            }
        });

    }

    public void registerlink(View view) {
    Intent intent = new Intent(getApplicationContext(), PersonalInfoActivity.class);
    startActivity(intent);
    }
}