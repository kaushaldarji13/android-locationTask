package com.example.googlemap;

import static java.lang.Double.parseDouble;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemap.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

//    EditText lat , lon ;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
//    Button find_location;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        lat = findViewById(R.id.latitude);
//        lon = findViewById(R.id.longitude);
//        find_location = findViewById(R.id.locationButton);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        try {
            if (binding.latitude.getText().toString()== null && binding.longitude.getText().toString()== null){
                Toast.makeText(MapsActivity.this,"enter values properly",Toast.LENGTH_SHORT).show();

            }
            else {
                latitude = ParseDouble(binding.latitude.getText().toString());
                longitude = ParseDouble(binding.longitude.getText().toString());


            }
        }catch (Exception e){
            e.printStackTrace();
        }




        binding.locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mMap = googleMap;
                    LatLng latLng = new LatLng(latitude, longitude);
                    Toast.makeText(MapsActivity.this,latitude +" "+longitude,Toast.LENGTH_SHORT).show();
//                    Log.d("lat", String.valueOf(latitude));
//                    Log.d("lon",String.valueOf(longitude));
                    mMap.addMarker(new MarkerOptions().position(latLng));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 2);
                    googleMap.animateCamera(cameraUpdate);
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    double ParseDouble(String strNumber) {
        if (strNumber == null ) {
            return 0;
        }
        else {
            try {
                return Double.parseDouble(strNumber);
            } catch(NumberFormatException e) {
                e.printStackTrace();
                  // or some value to mark this field is wrong. or make a function validates field first ...
                return 0;

            }
        }
    }


}