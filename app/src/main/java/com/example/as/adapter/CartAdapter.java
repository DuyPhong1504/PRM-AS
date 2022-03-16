package com.example.as.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.Utils;
import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.interfaces.OnAddCardClick;
import com.example.as.model.AppUtil;
import com.example.as.model.CartItem;
import com.example.as.model.Shoe;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<CartItem> items;
    int userId;
    private OnAddCardClick click;

    public CartAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.dong_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = items.get(position);
        holder.tvProductName.setText(item.getProduct().getName());
        holder.tvProductPrice.setText(item.getProduct().getPrice() + "");
        holder.etItemQuantity.setText(item.getQuantity()+"");
        holder.btnAddMore.setOnClickListener(view -> {
            holder.etItemQuantity.setText(item.getQuantity() + 1 + "");
            Utils.updateCart(item.getId(), userId, item.getQuantity() + 1);
            click.onReloadTotal();
        });
        holder.btnReduce.setOnClickListener(view -> {
            holder.etItemQuantity.setText((item.getQuantity() - 1 > 0 ? item.getQuantity() - 1 : 1) + "");
            Utils.updateCart(item.getId(), userId, (item.getQuantity() - 1 > 0 ? item.getQuantity() - 1 : 1));
            click.onReloadTotal();
        });
        holder.btnRemove.setOnClickListener(view -> {
            Utils.removeCart(item, userId);
            items.remove(position);
            click.onReloadTotal();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProductPrice, tvProductDescription;
        EditText etItemQuantity;
        Button btnAddMore, btnReduce;
        MaterialButton btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            etItemQuantity = itemView.findViewById(R.id.etCartItemQuantity);
            btnAddMore = itemView.findViewById(R.id.btnAddMore);
            btnReduce = itemView.findViewById(R.id.btnReduce);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }

    public void setClick(OnAddCardClick click) {
        this.click = click;
    }
}
