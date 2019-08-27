package com.theankhguide.darkmoon;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

            textTitle = (TextView) myView.findViewById(R.id.media_title);
            desc = (TextView) myView.findViewById(R.id.media_result_desc);
            imageView = (ImageView) myView.findViewById(R.id.media_result_imageView);
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
        try {
            String rTitle = dataList.get_collection().get_items().get(position).get_data().get(0).get_title();
            String rDesc = dataList.get_collection().get_items().get(position).get_data().get(0).get_description();
            String url = dataList.get_collection().get_items().get(position).get_links().get(0).get_href();

            holder.textTitle.setText(rTitle);

            Log.d("search", "loadDataList: ***" + rDesc);
            holder.desc.setText(rDesc);
//            holder.desc.setText(dataList.get_collection().get_items().get(position).get_data().get(0).get_description());

            String cleanUrl = url.replaceAll("\\s", "%20");
            Log.d("search", "loadDataList: ***" + cleanUrl);
            Picasso.get().load(url).into(holder.imageView);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Calculate the item count for the RecylerView//
    @Override
    public int getItemCount() {
        return dataList.get_collection().get_items().size();
    }
}
