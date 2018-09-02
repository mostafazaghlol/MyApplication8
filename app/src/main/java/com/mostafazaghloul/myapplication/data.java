package com.mostafazaghloul.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class data extends StringRequest {
    private static final String Url = "http://unicitaksa.com/WebServices/User/ManyOrders.php";
    private Map<String, String> params;

    public data(ArrayList<String> Product_Name
            , ArrayList<String> Prices
            , ArrayList<String> Quantities
            , String Seller_id
            , String Seller_name
            , String Customer_id
            , String Customer_name
            , String Customer_phone
            , String Lat
            , String Lan
            , String Address
            , String Comment
            , Response.Listener<String> listener){
        super(Method.POST,Url, listener,null);
        params = new HashMap<>();
        for(int i=0;i<Product_Name.size();i++){
            String product_name = "Product_Name["+i+"]";
            String price = "Price["+i+"]";
            String Quantity = "Quantity["+i+"]";
            params.put(product_name,Product_Name.get(i));
            params.put(price,Prices.get(i));
            params.put(Quantity,Quantities.get(i));
        }

        params.put("Seller_id",Seller_id);
        params.put("Seller_name",Seller_name);
        params.put("Customer_id",Customer_id);
        params.put("Customer_name",Customer_name);
        params.put("Customer_phone",Customer_phone);
        params.put("Lat",Lat);
        params.put("Lan",Lan);
        params.put("Address",Address);
        params.put("Comment",Comment);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
