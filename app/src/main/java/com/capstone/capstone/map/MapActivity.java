package com.capstone.capstone.map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.capstone.capstone.R;
import com.capstone.capstone.map.models.GpsTracker;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    GpsTracker mGpsTracker;
    FusedLocationSource mLocationSource;
    MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        init();
    }

    void init(){
        mMapView = findViewById(R.id.map_view);
        mMapView.getMapAsync(this);
        //mLocationSource = new FusedLocationSource(this, 1000);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        mGpsTracker = new GpsTracker(this);
        double latitude = mGpsTracker.getLatitude();    // 위도
        double longitude = mGpsTracker.getLongitude();  // 경도
        LatLng location = new LatLng(latitude, longitude);  // 현재 위치 객체

        CameraPosition cameraPosition = new CameraPosition(location, 9.5);   // 카메라 위치, 줌 조절, 10km 조절, 11.1
        naverMap.setCameraPosition(cameraPosition);

        //naverMap.setLocationSource(mLocationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
    }

}