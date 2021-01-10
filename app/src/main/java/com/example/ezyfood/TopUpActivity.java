package com.example.ezyfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        Button topupButton = findViewById(R.id.btn_topup_2);
        topupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText saldoText = findViewById(R.id.topup_saldo);
                int saldo = Integer.parseInt(saldoText.getText().toString());

                SharedPreferences sharedpreferences = getSharedPreferences("mySP", 0);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if(sharedpreferences.contains("saldo")) {
                    int currentSaldo = sharedpreferences.getInt("saldo", 0);
                    currentSaldo += saldo;
                    editor.putInt("saldo", currentSaldo);
                } else {
                    editor.putInt("saldo", saldo);
                }

                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}