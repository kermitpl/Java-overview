package com.example.adrian.myweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;
    LocationManager locationManager;
    String provider = LocationManager.NETWORK_PROVIDER;
    boolean canGPS = false;
    Button buttonGPS;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mContext = this.getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.currencies_list);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<CityCoord>(this, android.R.layout.simple_spinner_dropdown_item, CityCoord.values()));

        buttonGPS = (Button) findViewById(R.id.button);
        String emoji = getEmojiByUnicode(0x1F321);
        buttonGPS.setText(emoji+" Your location weather "+emoji);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkLocationPermission() == false)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        else locationManager.requestLocationUpdates(provider, 400, 10, locationListener);
    }

    private String getEmojiByUnicode(int i) {
        return new String(Character.toChars(i));
    }

    public boolean checkLocationPermission() {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            if (location != null) {
                fetchData(location.getLatitude(), location.getLongitude());
                Toast.makeText(mContext, "Refreshed your location.", Toast.LENGTH_LONG).show();
                locationManager.removeUpdates(locationListener);
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    locationManager.requestLocationUpdates(provider, 400, 10, locationListener);
                } else {
                    buttonGPS.setClickable(false);
                    buttonGPS.setText("No permissions to use GPS.");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void fetchData(double latitude, double longitude) {
        WeatherApi api = new WeatherApi(new OnWeatherReceived() {
            @Override
            public void onDataHandle(Weather weatherData, Wind windData, Coordinates coordinatesData, Icon iconData) {
                mRecyclerView.setAdapter(new MyAdapter(weatherData, windData, coordinatesData, iconData, mRecyclerView));
            }
        }, mContext);
        api.fetchData(latitude, longitude);
    }


    public void buttonOnClick(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location !=null) {
            fetchData(location.getLatitude(), location.getLongitude());
            Toast.makeText(mContext, "There you go sunshine ;)", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonOnClick2(View view) {
        if (spinner.getSelectedItem() != null) {
            CityCoord cityCoord = (CityCoord) spinner.getSelectedItem();
            Coordinates location = cityCoord.getCoordinates();
            if (location != null) {
                fetchData(location.getLat(), location.getLon());
                Toast.makeText(mContext, "There you go sunshine ;)", Toast.LENGTH_LONG).show();
            }
        }
        else Toast.makeText(mContext, "Select some city ;)", Toast.LENGTH_LONG).show();
    }

}
