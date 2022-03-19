package com.example.as.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.OrderDetail;
import com.example.as.R;
import com.example.as.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }


    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order_line,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvOrderId.setText(order.getId());
        holder.tvOrderTotal.setText(order.getTotals()+"");
        holder.tvOrderDate.setText(order.getId());
        holder.btnDetails.setOnClickListener(view -> {
            Intent intent = new Intent(context, OrderDetail.class);
            intent.putExtra("orderId", order.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0: orderList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvOrderId, tvOrderDate, tvOrderTotal;
        Button btnDetails;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
}
