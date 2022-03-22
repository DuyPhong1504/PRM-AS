package com.example.as.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.R;
import com.example.as.model.CartItem;

import java.util.List;

public class CheckoutItemAdapter extends RecyclerView.Adapter<CheckoutItemAdapter.CheckoutItemViewHolder>{
    private Context context;
    private List<CartItem> itemList;

    public CheckoutItemAdapter(Context context, List<CartItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CheckoutItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckoutItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_checkout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutItemViewHolder holder, int position) {
        CartItem item = itemList.get(position);
        holder.tvProductName.setText(item.getProduct().getName());
        holder.tvProductPrice.setText("Price: " + item.getProduct().getPrice() + "");
        holder.tvProductQuantity.setText("Quantity: "+item.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public class CheckoutItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductPrice, tvProductQuantity, tvProductName;
        public CheckoutItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}
