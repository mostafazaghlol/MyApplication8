package com.mostafazaghloul.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddorRemoveCallbacks {
    public static ArrayList<String> ACitems = new ArrayList<>();
    public static ArrayList<String> AcPrice= new ArrayList<>();
    public static ArrayList<String> ACQuantity = new ArrayList<>();
    ArrayList<String>  items=new ArrayList<>();
    ArrayList<String>  Prices=new ArrayList<>();
    ArrayList<String>  Quantity=new ArrayList<>();
    private List<dataIn> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private dataAdapter mAdapter;
    private String LOGTAG = MainActivity.class.getSimpleName();
    private static int cart_count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items.add("طعميه");
        items.add("فول");
        items.add("بطاطس");
        items.add("بطنجان");
        items.add("قلقاس");
        Prices.add("1");
        Prices.add("2");
        Prices.add("3");
        Prices.add("4");
        Prices.add("5");
        Quantity.add("27");
        Quantity.add("30");
        Quantity.add("35");
        Quantity.add("40");
        Quantity.add("45");



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new dataAdapter(movieList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,cart_count,R.drawable.ic_shopping_cart_white_24dp));
       return true;

    }
    @Override
    public void onAddProduct() {
        cart_count++;
        invalidateOptionsMenu();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.cart_action){
            if(ACitems.size()==0){
                Toast.makeText(this, "لا توجد بضاعه ف السله !", Toast.LENGTH_SHORT).show();
            }else{
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(LOGTAG,response);
                    }
                };
                data mData
                        =new data(ACitems,AcPrice,ACQuantity
                        ,"1","mostafa"
                        ,"2","mostafas"
                        ,"011488888","5254.55","2255","hiddsf","comment",listener);
                RequestQueue mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                mRequestQueue.add(mData);
                mRequestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
                    @Override
                    public void onRequestFinished(Request<Object> request) {
                        Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();
    }
    private void prepareMovieData() {
        for(int i=0;i<items.size();i++){
            dataIn mDataIn = new dataIn(items.get(i)
            ,Prices.get(i)
            ,Quantity.get(i));
            movieList.add(mDataIn);
        }
        mAdapter.notifyDataSetChanged();
    }
}
