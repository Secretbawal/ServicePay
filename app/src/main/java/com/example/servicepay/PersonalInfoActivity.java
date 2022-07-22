package com.example.servicepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class PersonalInfoActivity extends AppCompatActivity {
    EditText nameinput, mobnuminput, emailinput;

    Button submitbtn, openbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        nameinput = findViewById(R.id.name);
        mobnuminput = findViewById(R.id.phonenum);
        emailinput = findViewById(R.id.emailadd);
        submitbtn = findViewById(R.id.btnsubmit);
        openbtn = findViewById(R.id.btnopen);
        DbFunctions dbFunctions = new DbFunctions();

        Records rec_edit = (Records) getIntent().getSerializableExtra("update");
        if(rec_edit != null) {
            submitbtn.setText("UPDATE");
            nameinput.setText(rec_edit.getFullname());
            mobnuminput.setText(rec_edit.getMobilenumber());
            emailinput.setText(rec_edit.getEmailaddress());
            openbtn.setVisibility(View.GONE);
        } else {
            submitbtn.setText("SUBMIT");

        }

        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalInfoActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Records records = new Records(nameinput.getText().toString(), mobnuminput.getText().toString(), emailinput.getText().toString());
                if(rec_edit == null){
                dbFunctions.add(records).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PersonalInfoActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PersonalInfoActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                } else {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("fullname",nameinput.getText().toString());
                        hashMap.put("mobilenumber",mobnuminput.getText().toString());
                        hashMap.put("emailaddress",emailinput.getText().toString());
                        dbFunctions.update(rec_edit.getKey(),hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(PersonalInfoActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PersonalInfoActivity.this, "Data Update Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                }

            }
        });
    }


    public void goback(View view) {
        Intent intent = new Intent(getApplicationContext(), MobileOTPActivity.class);
        startActivity(intent);
    }
}