package com.example.as.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.as.activity.MainActivity;
import com.example.as.model.Cart_class;
import com.example.as.database.Database;
import com.example.as.R;
import com.example.as.activity.DetailShoe;
import com.example.as.model.AppUtil;
import com.example.as.model.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeAdapter  extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Shoe> shoeList;
    private Database database;

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
        TextView textViewQuantity;
        ImageView imageShoe;
        ImageView imageviewShoeDetail,imageviewAddtoCart;
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
            holder.textViewQuantity=(TextView) view.findViewById(R.id.textViewQuantity);
            holder.imageShoe=(ImageView) view.findViewById(R.id.imageviewShoe);
            holder.imageviewShoeDetail=(ImageView) view.findViewById(R.id.imageviewShoeDetail);
            holder.imageviewAddtoCart=(ImageView) view.findViewById(R.id.imageviewAddtoCart);
            view.setTag(holder);

            holder.imageviewShoeDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DetailShoe.class);
                    database=new Database(context,"GhiChu.sqlite",null,1);
                    int iddetail=i+1;
                    Cursor dataShoe=database.GetData("Select * from Shoe Where id = "+ iddetail + " LIMIT 1 ");
                    while (dataShoe.moveToNext()){
                        AppUtil.ShoeName=dataShoe.getString(1);
                        AppUtil.shoePrice=dataShoe.getString(2);
                        AppUtil.shoeDetail=dataShoe.getString(3);
                    }

                    context.startActivity(intent);
                }
            });

            holder.imageviewAddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(AppUtil.cart==null){
                        AppUtil.cart=new Cart_class();
                    }
                    AppUtil.cart.addShoe(shoeList.get(i));
                    Toast.makeText(context,"Add to cart success",Toast.LENGTH_LONG).show();
                    System.out.println(AppUtil.cart);


                }
            });

        }
        else
        {
            holder=(ViewHolder) view.getTag();
        }

        Shoe shoe=shoeList.get(i);
        String quantity="Quantity: "+String.valueOf(shoe.getQuantity());
        if(shoe.getQuantity()==1){
            quantity="";
        }

        holder.txtName.setText(shoe.getNameShoe());
        holder.txtPrice.setText("Price: "+shoe.getPrice() + "$");
        holder.textViewQuantity.setText(quantity);
        return view;
    }


}
