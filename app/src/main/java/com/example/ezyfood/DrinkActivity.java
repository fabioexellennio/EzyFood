package com.example.ezyfood;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public TextView tvlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        ArrayList<Drinks> drinkList = new ArrayList<>();
        Drinks drink1 = new Drinks();
        drink1.drinkName = "Air Mineral";
        drink1.drinkPrice = 123;
        drink1.drinkQty = 0;
        drinkList.add(drink1);

        Drinks drink2 = new Drinks();
        drink2.drinkName = "Jus Apel";
        drink2.drinkPrice = 123;
        drink2.drinkQty = 0;
        drinkList.add(drink2);

        Drinks drink3 = new Drinks();
        drink3.drinkName = "Jus Mangga";
        drink3.drinkPrice = 123;
        drink3.drinkQty = 0;
        drinkList.add(drink3);

        Drinks drink4 = new Drinks();
        drink4.drinkName = "Jus Alpukat";
        drink4.drinkPrice = 123;
        drink4.drinkQty = 0;
        drinkList.add(drink4);

//        String rio = "";
//        tvlist = findViewById(R.id.arraylist);
//        for (Drinks name:drinkList) {
//            rio += name.drinkName;
//            tvlist.setText(rio);
//        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        mAdapter = new DrinksAdapter(this, drinkList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
