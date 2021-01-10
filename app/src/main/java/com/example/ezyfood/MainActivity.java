package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("mySP", 0);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        TextView saldoTV = findViewById(R.id.saldo);

        if(sharedPreferences.contains("saldo")) {
            int saldo = sharedPreferences.getInt("saldo", 0);
            saldoTV.setText(String.valueOf(saldo));
        } else {
            saldoTV.setText("0");
        }
    }
    public void onClickDrinks(View view){
        Intent intent = new Intent(this, DrinkActivity.class);
        startActivity(intent);
    }
    public void onClickMyOrder(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }
    public void onClickMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void onClickTopUp(View view){
        Intent intent = new Intent(this, TopUpActivity.class);
        startActivity(intent);
    }
}