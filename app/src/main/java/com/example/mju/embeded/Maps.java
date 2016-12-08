package com.example.mju.embeded;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float latitude = 0; // 위도
    float longitude = 0; // 경도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        latitude = intent.getExtras().getFloat("param_latitude");
        //intent.getFloatExtra("param_latitude",latitude);
        longitude = intent.getExtras().getFloat("param_longitude");
        System.out.println("latitude & longitude = " + latitude + " & " + longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        // Add a marker in spot and move the camera
//        LatLng spot = new LatLng(37.398291, 126.963327);
        LatLng spot = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(spot).title("스팟").snippet("spot")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spot));

        // UI settings
        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setCompassEnabled(true);
//        mapSettings.setIndoorLevelPickerEnabled(true);

        // my location

        // zoom in properly to spot
        mMap.animateCamera(zoom);

        // 마커클릭 이벤트 처리
        // GoogleMap 에 마커클릭 이벤트 설정 가능.
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 마커 클릭시 호출되는 콜백 메서드
                Toast.makeText(getApplicationContext(),
                        marker.getTitle() + " is hot place!"
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
