package ru.startandroid.develop.p0541customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Freeman on 30.01.2017.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Product> mProducts;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MyAdapter(Context mContext, ArrayList<Product> mProducts) {
        this.mProducts = mProducts;
        this.mContext = mContext;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return mProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public Product getProduct(int i){
        return (Product)getItem(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = view;
        if (item == null){
            item = mLayoutInflater.inflate(R.layout.item, viewGroup, false);
        }
        Product product = getProduct(i);

        CheckBox cbItemCheckd = (CheckBox) item.findViewById(R.id.cbItemChecked);
        TextView tvItemTitle = (TextView) item.findViewById(R.id.tvItemTitle);
        TextView tvItemDesc = (TextView) item.findViewById(R.id.tvItemDesc);
        ImageView ivItemImage = (ImageView) item.findViewById(R.id.ivItemImage);

        tvItemTitle.setText(product.getName());
        tvItemDesc.setText(product.getPrice());
        ivItemImage.setImageResource(product.getImage());

        cbItemCheckd.setOnCheckedChangeListener(new MyCheckChangeListener());
        cbItemCheckd.setTag(i);
        cbItemCheckd.setChecked(product.isBox());

        return item;
    }

    public ArrayList<Product> getBox(){
        ArrayList<Product> box = new ArrayList<>();
        for (Product product: mProducts){
            if (product.isBox()){
                box.add(product);
            }
        }
        return box;
    }

    private class MyCheckChangeListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            getProduct((Integer)compoundButton.getTag()).setBox(b);
        }
    }
}
