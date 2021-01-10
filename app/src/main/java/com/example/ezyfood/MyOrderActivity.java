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

public class MyOrderActivity extends AppCompatActivity {
    TextView totalPrice;
    ArrayList<Drinks> drinkList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Drinks drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

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
        mLayoutManager = new GridLayoutManager(this,1);
        mAdapter = new MyOrderAdapter(this, drinkList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void onClickPayNow(View view){
        Intent intent = new Intent(this, OrderCompleteActivity.class);
        intent.putExtra("drink_list", drinkList);
        startActivity(intent);
    }

}