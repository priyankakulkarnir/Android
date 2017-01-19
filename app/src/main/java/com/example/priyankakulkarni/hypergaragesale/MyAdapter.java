package com.example.priyankakulkarni.hypergaragesale;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priyankakulkarni.garagesale.R;

import java.util.ArrayList;

/**
 * Created by priyankakulkarni on 10/17/16.
 */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private ArrayList<GarageItem> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView itemTextView;
        public TextView priceTextView;
        public TextView description;
        public TextView emailAddress;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            itemTextView = (TextView) v.findViewById(R.id.item_name);
            priceTextView = (TextView) v.findViewById(R.id.item_price);
            description = (TextView) v.findViewById(R.id.description);
            emailAddress = (TextView) v.findViewById(R.id.emailAddress);
            image = (ImageView) v.findViewById(R.id.image);

        }
    }

    public MyAdapter( ArrayList<GarageItem> myDataset) {

        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        GarageItem garageItem = mDataset.get(position);

        TextView textView = holder.itemTextView;
        TextView textView1 = holder.priceTextView;
        TextView textView2 = holder.description;
        TextView textView3 = holder.emailAddress;

        textView.setText(garageItem.getItemName());
        textView1.setText(String.valueOf(garageItem.getPrice()));
        textView2.setText(garageItem.getDescription());
        textView3.setText(garageItem.getEmalAdd());

    }

    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }

}
