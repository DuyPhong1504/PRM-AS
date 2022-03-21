package com.example.as.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.OrderDetail;
import com.example.as.R;
import com.example.as.activity.DetailShoe;
import com.example.as.model.Order;
import com.example.as.model.OrderItem;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    private Context context;
    private List<OrderItem> itemList;

    public OrderItemAdapter(Context context, List<OrderItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem item = itemList.get(position);
        holder.tvCheckoutProduct.setText(item.getProduct().getName());
        holder.tvCheckoutPrice.setText("Checkout price: "+ item.getPrice()+"");
        holder.tvCheckoutQuantity.setText("Quantity: " + item.getQuantity()+"");
        holder.tvCheckoutProduct.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailShoe.class);
            intent.putExtra("productId", item.getProduct().getProductId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return itemList == null ?   0 : itemList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCheckoutPrice, tvCheckoutQuantity, tvCheckoutProduct;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCheckoutPrice = itemView.findViewById(R.id.tvCheckoutPrice);
            tvCheckoutQuantity = itemView.findViewById(R.id.tvCheckoutQuantity);
            tvCheckoutProduct = itemView.findViewById(R.id.tvCheckoutProduct);
        }
    }
}
