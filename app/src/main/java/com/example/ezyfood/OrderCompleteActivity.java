package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrderCompleteActivity extends AppCompatActivity {
    TextView totalPrice;
    ArrayList<Drinks> drinkList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Drinks drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

        Intent intent  = getIntent();
        drinkList = (ArrayList<Drinks>) intent.getSerializableExtra("drink_list");

        totalPrice = findViewById(R.id.txt_total_price);

        int price = 0;
        for (Drinks drink:drinkList) {
            price += drink.drinkPrice * drink.drinkQty;
        }

        String totalprice = "Total Price : Rp " + price;
        totalPrice.setText(totalprice);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new OrderCompleteAdapter(this, drinkList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void mainMenu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}