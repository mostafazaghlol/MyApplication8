package com.mostafazaghloul.myapplication;

public class dataIn {
    private String name;
    private String price,quantity;
    private Boolean isAdded=false;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public dataIn(String name,String price,String quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public boolean isAddedTocart() {
        return isAdded;
    }

    public void setAdded(Boolean added) {
        isAdded = added;
    }
}
