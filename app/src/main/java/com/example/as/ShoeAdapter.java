package com.example.as;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShoeAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Shoe> shoeList;

    public ShoeAdapter(Context context, int layout, ArrayList<Shoe> arrayShoe) {
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
        TextView txtName;
        TextView txtPrice;
        ImageView imageShoe;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtName=(TextView) view.findViewById(R.id.textViewName);
            holder.txtPrice=(TextView) view.findViewById(R.id.textViewPrice);
            holder.imageShoe=(ImageView) view.findViewById(R.id.imageviewShoe);

            view.setTag(holder);

        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }
        Shoe shoe=shoeList.get(i);
        holder.txtName.setText(shoe.getNameShoe());
        holder.txtPrice.setText(shoe.getPrice());
        return view;
    }


}
