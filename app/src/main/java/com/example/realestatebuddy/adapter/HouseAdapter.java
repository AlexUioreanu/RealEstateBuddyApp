package com.example.realestatebuddy.adapter;

import android.annotation.SuppressLint;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestatebuddy.R;
import com.example.realestatebuddy.model.Distance;
import com.example.realestatebuddy.model.DistanceDao;
import com.example.realestatebuddy.model.House;
import com.example.realestatebuddy.model.HouseDataBase;
import com.example.realestatebuddy.model.MyLocation;
import com.example.realestatebuddy.model.MyLocationDao;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> implements Filterable {
    public static int itemCounter = 0;
    private final List<House> houseList;
    private final List<House> houseListFull;

    private MyLocationDao myLocationDao;
    private DistanceDao myDistanceDao;

    private final Filter theFilterd = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<House> filterdList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filterdList.addAll(houseListFull);
            } else {
                String[] filterPattern = charSequence.toString().toLowerCase().trim().split(" ");
                for (House house : houseListFull) {
                    for (String pat : filterPattern) {
                        if (house.getCity().toLowerCase().contains(pat) || house.getZip().toLowerCase().contains(pat)) {
                            filterdList.add(house);
                        }
                        if (house.getCity().toLowerCase().contains(pat) && house.getZip().toLowerCase().contains(pat)) {
                            filterdList.add(house);
                        }
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdList;
            itemCounter = filterdList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            houseList.clear();
            houseList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public HouseAdapter(List<House> houseList) {
        this.houseList = houseList;
        houseListFull = new ArrayList<>(houseList);
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false);

        HouseDataBase houseDataBase = HouseDataBase.getInstance(itemView.getContext());
        myLocationDao = houseDataBase.getMyLocationDao();
        myDistanceDao = houseDataBase.getMyDistance();


        return new HouseViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        House currentHouse = houseList.get(position);
        holder.setLocationId(currentHouse.getId());

        if (currentHouse.isFavorite()) {
            holder.getBtFavorite().setImageResource(R.drawable.ic_baseline_whatshot_24);
        }

        holder.setFavorite(currentHouse.isFavorite());
        Picasso.get().load(currentHouse.getImage()).into(holder.getImage());

        MyLocation m = myLocationDao.getLocation(1);
        if (m != null) {
            LatLng myHouseLocation = new LatLng(m.getLat(), m.getLon());
            LatLng houseLocation = new LatLng(currentHouse.getLatitude(), currentHouse.getLongitude());

            Location locationhouse = new Location("locationhouse");
            locationhouse.setLatitude(houseLocation.latitude);
            locationhouse.setLongitude(houseLocation.longitude);

            Location myLocation = new Location("MyLocation");
            myLocation.setLatitude(myHouseLocation.latitude);
            myLocation.setLongitude(myHouseLocation.longitude);

            int distance = (int) myLocation.distanceTo(locationhouse);
            int distanceKm = (int) (distance * 0.001);

            Distance distanceInKm = new Distance(currentHouse.getId(), distanceKm);

            myDistanceDao.addDistance(distanceInKm);

            holder.getGpsNr().setText(String.valueOf(distanceKm) + " km");
        }

        //Format the number to add commas
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formatNr = formatter.format(currentHouse.getPrice());
        String priceString = "$" + formatNr;
        holder.getPrice().setText(priceString);

        holder.getCity().setText(currentHouse.getCity());
        holder.getZip().setText(currentHouse.getZip());
        holder.getBedNr().setText(Integer.toBinaryString(currentHouse.getBedrooms()));
        holder.getBathNr().setText(Integer.toBinaryString(currentHouse.getBathrooms()));
        holder.getSurfaceNr().setText(Integer.toString(currentHouse.getSize()));
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    @Override
    public Filter getFilter() {
        return theFilterd;
    }
}
