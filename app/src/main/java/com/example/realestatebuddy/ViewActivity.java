package com.example.realestatebuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.realestatebuddy.model.Distance;
import com.example.realestatebuddy.model.DistanceDao;
import com.example.realestatebuddy.model.House;
import com.example.realestatebuddy.model.HouseDao;
import com.example.realestatebuddy.model.HouseDataBase;
import com.example.realestatebuddy.model.MyLocationDao;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ViewActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private ImageView img;
    private TextView price, bedNr, surfaceNr, bathNr, gpsNr, description;
    private House house;
    private Button backBt;
    private Bundle extras;
    private MyLocationDao myLocationDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        initializeComponents();

        HouseDataBase houseDataBase = HouseDataBase.getInstance(getApplicationContext());
        HouseDao houseDao = houseDataBase.getHouseDao();
        DistanceDao distanceDao = houseDataBase.getMyDistance();
        myLocationDao = houseDataBase.getMyLocationDao();

        extras = getIntent().getExtras();
        int houseId = extras.getInt(HouseDataBase.DATABASE_NAME);
        house = houseDao.getHouse(houseId);


        Picasso.get().load(Uri.parse(house.getImage())).into(img);

        //Format the number to add commas
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatNr = formatter.format(house.getPrice());
        String priceString = "$" + formatNr;
        price.setText(priceString);

        bedNr.setText(String.valueOf(house.getBedrooms()));
        surfaceNr.setText(String.valueOf(house.getSize()));
        bathNr.setText(String.valueOf(house.getBathrooms()));
        description.setText(house.getDescription());

        Distance distance = distanceDao.getDistance(houseId);
        String distanceString = String.valueOf(distance.getDistanceKm()) + " km";
        gpsNr.setText(distanceString);

        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                finish();
            }
        });

    }


    private void initializeComponents() {
        price = findViewById(R.id.view_price);
        bedNr = findViewById(R.id.view_bedNr);
        surfaceNr = findViewById(R.id.view_surfaceNr);
        bathNr = findViewById(R.id.view_bathNr);
        gpsNr = findViewById(R.id.view_gpsNr);
        description = findViewById(R.id.view_description);
        img = findViewById(R.id.view_image);
        backBt = findViewById(R.id.view_backBt);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng house = new LatLng(this.house.getLatitude(), this.house.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(house));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(house, 15));
    }

    //if you press the marker you can enter Maps and get directions
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }
}
