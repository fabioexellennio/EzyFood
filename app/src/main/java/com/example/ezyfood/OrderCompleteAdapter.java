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

public class OrderCompleteAdapter extends RecyclerView.Adapter<OrderCompleteAdapter.OrderCompleteViewHolder> {
    Context ctx;
    ArrayList<Drinks> drinkList;

    public class OrderCompleteViewHolder extends RecyclerView.ViewHolder{
        public TextView DrinkNameView;
        public TextView DrinkPriceView;

        public OrderCompleteViewHolder(@NonNull View itemView) {
            super(itemView);
            DrinkNameView = itemView.findViewById(R.id.txt_drink_name);
            DrinkPriceView = itemView.findViewById(R.id.txt_drink_qtyxprice);
        }
    }

    public OrderCompleteAdapter(Context ctx, ArrayList<Drinks> drinkList){
        this.ctx = ctx;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public OrderCompleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.order_complete_row, parent, false);
        OrderCompleteViewHolder ovh = new OrderCompleteViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCompleteViewHolder holder, int position) {
        Drinks drink1 = drinkList.get(position);
        holder.DrinkNameView.setText(drink1.drinkName);
        String drinkPrice = drink1.drinkQty +  " x Rp. " + drink1.drinkPrice;
        holder.DrinkPriceView.setText(drinkPrice);
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}

