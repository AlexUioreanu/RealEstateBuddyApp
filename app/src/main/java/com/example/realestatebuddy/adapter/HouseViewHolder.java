package com.example.realestatebuddy.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestatebuddy.R;
import com.example.realestatebuddy.ViewActivity;
import com.example.realestatebuddy.model.HouseDao;
import com.example.realestatebuddy.model.HouseDataBase;
import com.example.realestatebuddy.model.MyLocationDao;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class HouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final HouseDao houseDao;
    private final MyLocationDao myLocationDao;
    private ImageView image;
    private TextView price, city, zip, bedNr, surfaceNr, bathNr, gpsNr;
    private ImageButton btFavorite;
    private int locationId;
    private boolean isFavorite;
    private CardView mLayout;


    public HouseViewHolder(@NonNull View itemView) {
        super(itemView);

        HouseDataBase locationDataBase = HouseDataBase.getInstance(itemView.getContext());
        houseDao = locationDataBase.getHouseDao();

        HouseDataBase houseDataBase = HouseDataBase.getInstance(itemView.getContext());
        myLocationDao = houseDataBase.getMyLocationDao();

        initializeComponents(itemView);

        btFavorite.setOnClickListener(v -> {
            if (!isFavorite) {
                houseDao.setFavorite(locationId);
                setFavorite(true);
                btFavorite.setImageResource(R.drawable.ic_baseline_whatshot_24);
                Snackbar.make(v, v.getContext().getString((R.string.favorites_added)), BaseTransientBottomBar.LENGTH_SHORT).show();
            } else {
                houseDao.removeFavorite(locationId);
                setFavorite(false);
                btFavorite.setImageResource(R.drawable.ic_whatshot_light);
                Snackbar.make(v, v.getContext().getString(R.string.favorites_removed), BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        mLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(view.getContext(), ViewActivity.class);
        i.putExtra(HouseDataBase.DATABASE_NAME, this.getLocationId());
        view.getContext().startActivity(i);
    }

    private void initializeComponents(@NonNull View itemView) {
        image = itemView.findViewById(R.id.item_image);
        price = itemView.findViewById(R.id.item_price);
        city = itemView.findViewById(R.id.item_city);
        zip = itemView.findViewById(R.id.item_zip);
        bedNr = itemView.findViewById(R.id.item_bedNr);
        surfaceNr = itemView.findViewById(R.id.item_surfaceNr);
        bathNr = itemView.findViewById(R.id.item_bathNr);
        gpsNr = itemView.findViewById(R.id.item_gpsNr);
        btFavorite = itemView.findViewById(R.id.item_favorite);
        mLayout = itemView.findViewById(R.id.location_item);
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getPrice() {
        return price;
    }

    public TextView getCity() {
        return city;
    }

    public TextView getZip() {
        return zip;
    }

    public TextView getBedNr() {
        return bedNr;
    }

    public TextView getSurfaceNr() {
        return surfaceNr;
    }

    public TextView getBathNr() {
        return bathNr;
    }

    public TextView getGpsNr() {
        return gpsNr;
    }

    public ImageButton getBtFavorite() {
        return btFavorite;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}
