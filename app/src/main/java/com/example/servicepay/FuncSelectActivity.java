package com.example.servicepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FuncSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_select);
        Button cashinbtn = findViewById(R.id.cashin);
        Button cashoutbtn = findViewById(R.id.cashout);
        Button loanbtn = findViewById(R.id.loan);
        Button paybillsbtn = findViewById(R.id.paybills);
        Button savemoneybtn = findViewById(R.id.savemoney);

        cashinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cashin.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(), MobileOTPActivity.class);
                startActivity(intent);
            case R.id.item2:
                Intent intent1 = new Intent(getApplicationContext(), MobileOTPActivity.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }
}