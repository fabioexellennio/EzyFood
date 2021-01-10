package com.example.ezyfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder> {
    Context ctx;
    ArrayList<Drinks> drinkList;

    public class DrinkViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView DrinkNameView;
        public TextView DrinkPriceView;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            DrinkNameView = itemView.findViewById(R.id.txt_drink_name);
            DrinkPriceView = itemView.findViewById(R.id.txt_drink_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Drinks drink = drinkList.get(position);

            Intent intent = new Intent(ctx, OrderActivity.class);
            intent.putExtra("key_value", drink);
//            Toast.makeText(ctx, drink.drinkName, Toast.LENGTH_LONG).show();
            ctx.startActivity(intent);
        }
    }

    public DrinksAdapter(Context ctx, ArrayList<Drinks> drinkList){
        this.ctx = ctx;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.drink_row, parent, false);
        DrinkViewHolder dvh = new DrinkViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        Drinks drink1 = drinkList.get(position);
        holder.DrinkNameView.setText(drink1.drinkName);
        String drinkPrice = "Rp. " + drink1.drinkPrice;
        holder.DrinkPriceView.setText(drinkPrice);
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}
