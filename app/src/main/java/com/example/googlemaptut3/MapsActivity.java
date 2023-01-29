package com.example.googlemaptut3;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaptut3.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        checkPermission();

        mMap = googleMap;

        LatLng schwartzCoords = new LatLng(45.51633019612566, -73.57766120229022);
        mMap.addMarker(new MarkerOptions().position(schwartzCoords).title("Schwartz's Deli"));

        LatLng altoCoords = new LatLng(45.50928374710736, -73.57274399944126);
        mMap.addMarker(new MarkerOptions().position(altoCoords).title("Alto Restaurant"));

        LatLng fiveguysCoords = new LatLng(45.500605799059606, -73.5591749546303);
        mMap.addMarker(new MarkerOptions().position(fiveguysCoords).title("Five Guys"));
        
        LatLng campoCoords = new LatLng(45.500546112716435, -73.57484281965722);
        mMap.addMarker(new MarkerOptions().position(campoCoords).title("Campo"));

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18f));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                //.target(userLocation)
                .target(altoCoords)
                .zoom(17f)
                .tilt(45f)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        }
    }

//    public void checkFinePermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mMap.setMyLocationEnabled(true);
//        } else {
//            ActivityCompat.requestPermissions(MapsActivity.this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 101);
//        }
//    }


}