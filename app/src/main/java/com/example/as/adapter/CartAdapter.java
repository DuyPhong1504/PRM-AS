package com.example.as.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.model.AppUtil;
import com.example.as.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Shoe> shoeList;
    private Database database;

    public CartAdapter(Context context, int layout, ArrayList<Shoe> arrayShoe) {
        this.context = context;
        this.layout = layout;
        this.shoeList = arrayShoe;
    }


    @Override
    public int getCount() {
        return shoeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtNameCart;
        TextView txtPriceCart;
        TextView textViewQuantityCart;
        ImageView ShoeImageCart, Delete,Add,Minus;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CartAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtNameCart = (TextView) view.findViewById(R.id.textViewNameCart);
            holder.txtPriceCart = (TextView) view.findViewById(R.id.textViewPriceCart);
            holder.textViewQuantityCart = (TextView) view.findViewById(R.id.textViewQuantityCart);
            holder.ShoeImageCart = (ImageView) view.findViewById(R.id.imageviewShoeCart);
            holder.Delete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.Add = (ImageView) view.findViewById(R.id.imgAdd);
            holder.Minus = (ImageView) view.findViewById(R.id.imgMinus);



            holder.Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtil.cart.removeBook(shoeList.get(i).getIdShoe());
                    ((Activity)context).recreate();
                }
            });

            holder.Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppUtil.cart.addShoe(shoeList.get(i));
                    ((Activity)context).recreate();
                }
            });
            holder.Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoeList.get(i).setQuantity((shoeList.get(i).getQuantity())-1);
                    if(shoeList.get(i).getQuantity()==0){
                        AppUtil.cart.removeBook(shoeList.get(i).getIdShoe());
                    }
                    ((Activity)context).recreate();
                }
            });

            view.setTag(holder);


        } else {
            holder = (CartAdapter.ViewHolder) view.getTag();
        }

        Shoe shoe = shoeList.get(i);
        String quantity = "Quantity: " + String.valueOf(shoe.getQuantity());
        holder.txtNameCart.setText(shoe.getNameShoe());
        holder.txtPriceCart.setText("Price: " + shoe.getPrice() + "$");
        holder.textViewQuantityCart.setText(quantity);
        return view;
    }


}
