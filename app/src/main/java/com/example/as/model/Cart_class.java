package com.example.as.model;


import com.example.as.model.Shoe;

import java.util.HashMap;

public class Cart_class extends HashMap {
    public Cart_class(){
        super();
    }

    public void addShoe(Shoe shoe){
        int key =shoe.getIdShoe();
        if(this.containsKey(key)){
            int oldQuantity= ((Shoe)this.get(key)).getQuantity();
            ((Shoe)this.get(key)).setQuantity(oldQuantity+1);

        }else {
            this.put(shoe.getIdShoe(),shoe);
        }
    }

    public boolean removeBook(int id){
        if(this.containsKey(id)){
            this.remove(id);
            return true;
        }
        return false;
    }
}
