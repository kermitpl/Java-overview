package com.example.adrian.myweatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter {

    private Weather mWeather = new Weather();
    private Wind mWind = new Wind();
    private Coordinates mCoordinates = new Coordinates();
    private Icon mIcon = new Icon();


    private RecyclerView mRecyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mPressure;
        public TextView mTemperature;
        public TextView mWindSpeed;
        public TextView mLatitude;
        public TextView mLongitude;
        public ImageView mImageView;

        public MyViewHolder(View pItem) {
            super(pItem);
            mPressure = (TextView) pItem.findViewById(R.id.weather_pressure);
            mTemperature = (TextView) pItem.findViewById(R.id.weather_temp);
            mWindSpeed = (TextView) pItem.findViewById(R.id.wind_speed);
            mLatitude = (TextView) pItem.findViewById(R.id.latitude);
            mLongitude = (TextView) pItem.findViewById(R.id.longitude);
            mImageView = (ImageView) pItem.findViewById(R.id.imageView);
        }
    }

    public MyAdapter(Weather pWeather, Wind pWind, Coordinates pCoordinates, Icon pIcon, RecyclerView pRecyclerView){
        mWeather = pWeather;
        mWind = pWind;
        mCoordinates = pCoordinates;
        mIcon = pIcon;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_layout, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        Weather weather = mWeather;
        Wind wind = mWind;
        Coordinates coordinates = mCoordinates;
        Icon icon = mIcon;
        ((MyViewHolder) viewHolder).mPressure.setText(String.valueOf(weather.getPressure()));
        ((MyViewHolder) viewHolder).mTemperature.setText(String.valueOf(weather.getTemp()));
        ((MyViewHolder) viewHolder).mWindSpeed.setText(String.valueOf(wind.getSpeed()));
        ((MyViewHolder) viewHolder).mLatitude.setText(String.valueOf(coordinates.getLat()));
        ((MyViewHolder) viewHolder).mLongitude.setText(String.valueOf(coordinates.getLon()));
        Context context = this.mRecyclerView.getContext();
        int id = context.getResources().getIdentifier("i"+icon.getIcon(), "drawable", context.getPackageName());
        ((MyViewHolder) viewHolder).mImageView.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}