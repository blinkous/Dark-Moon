package com.theankhguide.darkmoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyNasaMediaAdapter extends RecyclerView.Adapter<MyNasaMediaAdapter.CustomViewHolder>{
    private RetroNasaCollection dataList;

    public MyNasaMediaAdapter(RetroNasaCollection dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        //Get a reference to the Views in our layout//
        public final View myView;

        TextView textTitle;
        TextView desc;
        ImageView imageView;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textTitle = myView.findViewById(R.id.media_title);
            desc = myView.findViewById(R.id.media_desc);
            imageView = myView.findViewById(R.id.media_imageView);
        }
    }

    //Construct a RecyclerView.ViewHolder//
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.nasa_row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    //Set the data//
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textTitle.setText(dataList.get_collection().get_items().get(position).get_data().get(0).get_title());
        holder.desc.setText(dataList.get_collection().get_items().get(position).get_data().get(0).get_description());

//        holder.imageView.;
//        Picasso.get().load(dataList.get(position).get_items().get_links().get_href()).into(imageView);
    }

    //Calculate the item count for the RecylerView//
    @Override
    public int getItemCount() {
        return dataList.get_collection().get_items().size();
    }
}
