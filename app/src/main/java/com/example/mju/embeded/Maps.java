package com.example.mju.embeded;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.cast.Cast;
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
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            mMap.setMyLocationEnabled(true);
//            mapSettings.setMyLocationButtonEnabled(true);
//            return;
//        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
            return;
        }


        // zoom in properly to spot
        mMap.animateCamera(zoom);

        // map click
//        mMap.setOnMapClickListener();
//        mMap.setOnMapLongClickListener(this);
//        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
//            @Override
//            public void OnMapLongClick(LatLng spot)
//            {
//                Toast.makeText(getApplicationContext(),"Click : " + String.valueOf(spot.latitude)
//                        + " / " + String.valueOf(spot.longitude), Toast.LENGTH_LONG);
//            }
//        });

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

//    public void onMapClick(LatLng spot)
//    {
//        Toast.makeText(this,"Click : " + String.valueOf(spot.latitude) + " / "
//                + String.valueOf(spot.longitude), Toast.LENGTH_LONG);
//    }
//
//    public void onMapLongClick(LatLng spot)
//    {
//        Toast.makeText(this,"LongClick : " + String.valueOf(spot.latitude) + " / "
//                + String.valueOf(spot.longitude), Toast.LENGTH_LONG);
//    }
}
