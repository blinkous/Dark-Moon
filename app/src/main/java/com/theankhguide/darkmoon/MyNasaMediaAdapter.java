package com.theankhguide.darkmoon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyNasaMediaAdapter {
    private List<RetroNasaMedia> dataList;

    public MyPlantAdapter(List<RetroPlant> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        //Get a reference to the Views in our layout//
        public final View myView;

        TextView textSciName;
        TextView textCommonName;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textSciName = myView.findViewById(R.id.plantSciName);
            textCommonName = myView.findViewById(R.id.plantCommonName);
        }
    }

    //Construct a RecyclerView.ViewHolder//
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.plant_row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    //Set the data//
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textSciName.setText(dataList.get(position).getPlantSciName());
        holder.textCommonName.setText(dataList.get(position).getPlantCommonName());
    }

    //Calculate the item count for the RecylerView//
    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
