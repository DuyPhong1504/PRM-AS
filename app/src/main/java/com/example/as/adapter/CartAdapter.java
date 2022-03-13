package com.example.as;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.as.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Shoe> shoeList;
    private Database database;

    public CartAdapter(Context context, int layout, ArrayList<Shoe> arrayShoe) {
        this.context=context;
        this.layout=layout;
        this.shoeList=arrayShoe;
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

    private class ViewHolder{
        TextView txtNameCart;
        TextView txtPriceCart;
        TextView textViewQuantityCart;
        ImageView ShoeImageCart;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CartAdapter.ViewHolder holder;
        if(view == null){
            holder=new CartAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtNameCart=(TextView) view.findViewById(R.id.textViewNameCart);
            holder.txtPriceCart=(TextView) view.findViewById(R.id.textViewPriceCart);
            holder.textViewQuantityCart=(TextView) view.findViewById(R.id.textViewQuantityCart);
            holder.ShoeImageCart=(ImageView) view.findViewById(R.id.imageviewShoeCart);
            view.setTag(holder);



        }
        else
        {
            holder=(CartAdapter.ViewHolder) view.getTag();
        }

        Shoe shoe=shoeList.get(i);
        String quantity="Quantity: "+String.valueOf(shoe.getQuantity());
        holder.txtNameCart.setText(shoe.getNameShoe());
        holder.txtPriceCart.setText("Price: "+shoe.getPrice() + "$");
        holder.textViewQuantityCart.setText(quantity);
        return view;
    }


}
