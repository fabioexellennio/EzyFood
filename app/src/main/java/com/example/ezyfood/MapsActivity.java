package com.example.ezyfood;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private Location getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(
                MapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

            return null;
        } else {
            LocationManager mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            assert mLocationManager != null;
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }
            return bestLocation;
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = getLastKnownLocation();
            if (locationGPS != null) {
                latitude = locationGPS.getLatitude();
                longitude = locationGPS.getLongitude();

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getLocation();

        Location current = new Location("crntlocation");
        current.setLatitude(latitude);
        current.setLongitude(longitude);

        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        Restaurant bogor = new Restaurant();
        bogor.latitude = -6.585980;
        bogor.longitude = 106.817982;
        restaurantList.add(bogor);

        Restaurant bekasi = new Restaurant();
        bekasi.latitude = -6.237982;
        bekasi.longitude = 106.988670;
        restaurantList.add(bekasi);

        Restaurant tangsel = new Restaurant();
        tangsel.latitude = -6.295910;
        tangsel.longitude = 106.668673;
        restaurantList.add(tangsel);

        int nearestIndex = -1;
        double minDist = 1E38;

        for (int i = 0; i < restaurantList.size(); i++){
            Location newLoc = new Location("newLoc");
            newLoc.setLatitude(restaurantList.get(i).latitude);
            newLoc.setLongitude(restaurantList.get(i).longitude);
            double curDistance = current.distanceTo(newLoc);

            if (curDistance < minDist) {
                minDist = curDistance;
                nearestIndex = i;
            }
        }

        LatLng currentLocation = new LatLng(latitude, longitude);
        LatLng newLocation = new LatLng(restaurantList.get(nearestIndex).latitude, restaurantList.get(nearestIndex).longitude);

        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
        mMap.addMarker(new MarkerOptions().position(newLocation).title("New Location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

//        float zoomLevel = 16.0f; //This goes up to 21
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 10));
    }
}