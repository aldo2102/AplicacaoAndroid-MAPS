package com.aldoh.mapas;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements LocationListener {

    private LatLng localizacao ;//= new LatLng(-15.798537, -48.090975);
    private LocationManager locationManager;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pega a localização atual do dispositivo

        double longitude ;
        double latitude;


        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
        {
            // request location update!!
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, this);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }else{
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }


        localizacao = new LatLng(latitude,longitude);
        //seta o objeto MAP criado na interface.
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        //seta a posição e zoom

        map.addMarker(new MarkerOptions().position(localizacao).title("Apto Do Aldo"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacao, 20));
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
