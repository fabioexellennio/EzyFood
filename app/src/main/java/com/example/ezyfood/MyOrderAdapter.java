package com.example.ezyfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.OrderViewHolder> {
    Context ctx;
    ArrayList<Drinks> drinkList;

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        public TextView DrinkNameView;
        public TextView DrinkPriceView;
        public Button btn_hapus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            DrinkNameView = itemView.findViewById(R.id.txt_drink_name);
            DrinkPriceView = itemView.findViewById(R.id.txt_drink_qtyxprice);
            btn_hapus = itemView.findViewById(R.id.btn_hapus);

            btn_hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    drinkList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

    }

    public MyOrderAdapter(Context ctx, ArrayList<Drinks> drinkList){
        this.ctx = ctx;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.myorder_row, parent, false);
        OrderViewHolder ovh = new OrderViewHolder(v);
        return ovh;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
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
