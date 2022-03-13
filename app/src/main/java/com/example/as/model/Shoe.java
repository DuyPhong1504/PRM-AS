package com.example.as;

public class Shoe {
    private int IdShoe;
    private String NameShoe;
    private String price;
    private int Quantity;
    private String Detail;

    public Shoe(int idShoe, String nameShoe, String price) {
        IdShoe = idShoe;
        NameShoe = nameShoe;
        this.price = price;
        this.Quantity=1;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "IdShoe=" + IdShoe +
                ", NameShoe='" + NameShoe + '\'' +
                ", price='" + price + '\'' +
                ", Quantity=" + Quantity +
                ", Detail='" + Detail + '\'' +
                '}';
    }
}
