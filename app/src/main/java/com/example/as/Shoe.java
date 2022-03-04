package com.example.as;

public class Shoe {
    private int IdShoe;
    private String NameShoe;
    private String price;

    public Shoe(int idShoe, String nameShoe, String price) {
        IdShoe = idShoe;
        NameShoe = nameShoe;
        this.price = price;
    }

    public int getIdShoe() {
        return IdShoe;
    }

    public void setIdShoe(int idShoe) {
        IdShoe = idShoe;
    }

    public String getNameShoe() {
        return NameShoe;
    }

    public void setNameShoe(String nameShoe) {
        NameShoe = nameShoe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
