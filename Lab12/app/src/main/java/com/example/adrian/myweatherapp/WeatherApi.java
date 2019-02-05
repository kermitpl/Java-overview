package com.example.adrian.myweatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherApi {
    private OnWeatherReceived weatherReceived;

    private Context mContext;
    private Weather weatherData;
    private Wind windData;
    private Coordinates coordinatesData;
    private Icon iconData;
    private RequestQueue queue;

    public WeatherApi(OnWeatherReceived weatherReceived, Context pContext) {
        this.weatherReceived = weatherReceived;
        this.mContext = pContext;
        this.queue = Volley.newRequestQueue(pContext);
        this.weatherData = new Weather();
        this.windData = new Wind();
        this.coordinatesData = new Coordinates();
        this.iconData = new Icon();
    }

    public void fetchData(double lat, double lon){
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=metric&appid=e21fdcc8271fdae231028b657101e430";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest
                (Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            JSONObject wind = response.getJSONObject("wind");
                            JSONObject coord = response.getJSONObject("coord");
                            JSONArray icon1 = response.getJSONArray("weather");
                            String name = response.getString("name");
                            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
                            JSONObject icon = icon1.getJSONObject(0);
                            Gson gson = new Gson();
                            weatherData = gson.fromJson(main.toString(),  new TypeToken<Weather>(){}.getType());
                            windData = gson.fromJson(wind.toString(), new TypeToken<Wind>(){}.getType());
                            coordinatesData = gson.fromJson(coord.toString(), new TypeToken<Coordinates>(){}.getType());
                            iconData = gson.fromJson(icon.toString(), new TypeToken<Icon>(){}.getType());
                            weatherReceived.onDataHandle(weatherData, windData, coordinatesData, iconData);
                        } catch (JSONException e) {
                            Toast.makeText(mContext, "Could not parse server response", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, "Could not connect server: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonArrayRequest);
    }
}
