package com.example.as.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as.R;
import com.example.as.activity.AdminActivity;
import com.example.as.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    public AdminActivity context;
    private int layout;
    private List<Product> productList;

    public ProductAdapter(AdminActivity context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productList.get(i).getProductId();
    }

    private class ViewHolder {
        TextView tvProductName;
        ImageView ivDelete, ivEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            // Get view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            //----------------------------------
            holder.tvProductName = (TextView) view.findViewById(R.id.tvProductName);
//            holder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
//            holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product product = productList.get(i);
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.updateProductDialog(product.getProductId(), product);
            }
        });
        return view;
    }
}
