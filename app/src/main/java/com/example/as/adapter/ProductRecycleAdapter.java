package com.example.as.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as.R;
import com.example.as.interfaces.OnItemClickListener;
import com.example.as.model.Product;

import java.util.List;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;
    public OnItemClickListener itemListener;
    public ProductRecycleAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product found = productList.get(position);
        holder.tvProductName.setText(found.getName());
//        holder.tvProductPrice.setText("Price: " + found.getPrice());
//        holder.tvProductQuantity.setText("Quantity: " + found.getQuantity());
//        holder.tvProductDescription.setText("Description: " + found.getDescription());

        holder.llProductContainer.setOnClickListener(view -> {
            itemListener.onItemClick(found.getProductId());
        });

    }


//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Product found = productList.get(position);
//        holder.tvProductName.setText("Product name: " + found.getName());
//        holder.tvProductPrice.setText("Price: " + found.getPrice());
//        holder.tvProductQuantity.setText("Quantity: " + found.getQuantity());
//        holder.tvProductDescription.setText("Description: " + found.getDescription());
//    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvProductName;
        public LinearLayout llProductContainer;
        public TextView tvProductPrice;
        public TextView tvProductQuantity;
        public TextView tvProductDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            llProductContainer = itemView.findViewById(R.id.llProductContainer);
//            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
//            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
//            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
        }
    }

}
