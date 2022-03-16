package com.example.as.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.Utils;
import com.example.as.activity.MainActivity;
import com.example.as.interfaces.OnAddCardClick;
import com.example.as.model.CartItem;
import com.example.as.model.Cart_class;
import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.activity.DetailShoe;
import com.example.as.model.AppUtil;
import com.example.as.model.Product;
import com.example.as.model.Shoe;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ShoeAdapter  extends RecyclerView.Adapter<ShoeAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> shoeList;
    private int userId;
    private OnAddCardClick cardClick;

    public ShoeAdapter(Context context, List<Product> shoeList, int userId) {
        this.context = context;
        this.shoeList = shoeList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.dong_shoe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = shoeList.get(position);
        holder.tvProductName.setText(item.getName());
        holder.tvProductPrice.setText(item.getPrice()+"");
        holder.btnAddCart.setOnClickListener(view ->{
            CartItem cartItem = new CartItem(1, shoeList.get(position), 1);
            Utils.addCard(cartItem,userId,1);
            cardClick.onReloadTotal();
        });
        holder.product.setOnClickListener(view -> {
            Intent intent = new Intent(context,DetailShoe.class);
            intent.putExtra("userId", userId);
            intent.putExtra("productId", item.getProductId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shoeList == null ? 0 : shoeList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tvProductName, tvProductPrice, tvProductDescription;
        EditText etItemQuantity;
        MaterialButton btnAddCart;
        ConstraintLayout product;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            etItemQuantity = itemView.findViewById(R.id.etCartItemQuantity);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            btnAddCart = itemView.findViewById(R.id.btnAddCart);
            product = itemView.findViewById(R.id.product);
        }
    }

    public void setCardClick(OnAddCardClick cardClick) {
        this.cardClick = cardClick;
    }
}
