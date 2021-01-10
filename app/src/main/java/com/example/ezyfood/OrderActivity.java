package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ezyfood.data.OrderData;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    Drinks drink;
    EditText editQty;
    TextView tvDrinkName;
    TextView tvDrinkPrice;
    ArrayList<Drinks> drinkList;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        editQty = findViewById(R.id.input_qty);
        tvDrinkName = findViewById(R.id.tvDrinkName);
        tvDrinkPrice = findViewById(R.id.tvDrinkPrice);

        Intent intent = getIntent();
        drink = (Drinks) intent.getSerializableExtra("key_value");

        tvDrinkName.setText(drink.drinkName);
        String price = "Rp " + drink.drinkPrice;
        tvDrinkPrice.setText(price);

        drinkList = OrderData.drinkList;
    }

    public void onClickMyOrder(View view){
        Intent intent = new Intent(this, MyOrderActivity.class);
        drink.drinkQty = Integer.parseInt(editQty.getText().toString());
        drinkList.add(drink);
        intent.putExtra("drink_list", drinkList);
        startActivity(intent);
    }
    public void onClickOrderMore(View view){
        Intent intent = new Intent(this, DrinkActivity.class);
        drink.drinkQty = Integer.parseInt(editQty.getText().toString());
        drinkList.add(drink);
        intent.putExtra("drink_list", drinkList);
        startActivity(intent);
    }
}