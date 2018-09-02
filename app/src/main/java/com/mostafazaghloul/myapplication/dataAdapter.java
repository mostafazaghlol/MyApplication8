package com.mostafazaghloul.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.os.Build.VERSION_CODES.O;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {

    private List<dataIn> dataList;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, quantity;
        public Button buy;
        public EditText editText;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            quantity = (TextView) view.findViewById(R.id.Quantity);
            editText = (EditText) view.findViewById(R.id.numbers);
            buy = (Button) view.findViewById(R.id.buty);
        }
    }


    public dataAdapter(List<dataIn> dataList,Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        dataIn movie = dataList.get(position);
        holder.name.setText(movie.getName());
        holder.price.setText(String.valueOf(movie.getPrice()));
        holder.quantity.setText(String.valueOf(movie.getQuantity()));
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.editText.getText().toString().isEmpty()) {
                    if (!dataList.get(position).isAddedTocart()) {
                        dataList.get(position).setAdded(true);
                        holder.buy.setText("حذف");
                        if (mContext instanceof MainActivity) {
                            ((AddorRemoveCallbacks) mContext).onAddProduct();
                        }

                    } else {
                        dataList.get(position).setAdded(false);
                        holder.buy.setText("شراء");
                        holder.editText.setText("");
                        ((AddorRemoveCallbacks) mContext).onRemoveProduct();
                    }
                } else {
                    holder.editText.setError("ادخل الكميه المطلوبه!");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
