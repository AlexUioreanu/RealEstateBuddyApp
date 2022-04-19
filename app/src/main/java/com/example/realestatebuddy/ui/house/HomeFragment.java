package com.example.realestatebuddy.ui.house;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.realestatebuddy.R;
import com.example.realestatebuddy.adapter.HouseAdapter;
import com.example.realestatebuddy.model.House;
import com.example.realestatebuddy.model.HouseDao;
import com.example.realestatebuddy.model.HouseDataBase;
import com.example.realestatebuddy.retrofit.HouseApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String BASE_URL = "https://intern.development.d-tt.dev/api/";
    private static final String API_KEY = "98bww4ezuzfePCYFxJEWyszbUXc7dxRx";
    private static final String NOT_FOUND = "No result found! \n Perhaps try another search?";

    private HouseDao houseDao;
    private RecyclerView recyclerViewTrips;
    private HouseAdapter adapter;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView imgNothing;
    private TextView textNothing;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        HouseDataBase houseDataBase = HouseDataBase.getInstance(getContext());
        houseDao = houseDataBase.getHouseDao();

        initComponents(view);

        getDataAndInsertIt();

        showData(houseDao.getAllHouse());

        searchMethod();

        refresh();


        return view;
    }

    private void searchMethod() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                imgNothing.setImageResource(0);
                textNothing.setText("");
                adapter.getFilter().filter(s);
                if (HouseAdapter.itemCounter == 0) {
                    imgNothing.setImageResource(R.mipmap.nothingfound_foreground_foreground);
                    textNothing.setText(NOT_FOUND);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                imgNothing.setImageResource(0);
                textNothing.setText("");
                adapter.getFilter().filter(s);
                if (HouseAdapter.itemCounter == 0) {
                    imgNothing.setImageResource(R.mipmap.nothingfound_foreground_foreground);
                    textNothing.setText(NOT_FOUND);
                }
                return false;
            }
        });
    }

    private void refresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showData(houseDao.getAllHouse());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initComponents(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerViewTrips = view.findViewById(R.id.tripList);
        searchView = view.findViewById(R.id.search_bar);
        imgNothing = view.findViewById(R.id.image_nothing);
        textNothing = view.findViewById(R.id.text_nothing);
    }


    private void getDataAndInsertIt() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HouseApi api = retrofit.create(HouseApi.class);

        api.getAllLocations(API_KEY).enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, Response<List<House>> response) {
                ArrayList<House> list = new ArrayList<>();
                assert response.body() != null;
                list.addAll(response.body());
                for (House l : list) {

                    l.setImage("https://intern.development.d-tt.dev/uploads/house" + String.valueOf(l.getId() - 1) + ".jpg");

                    houseDao.addHome(l);
                }
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void showData(List<House> houses) {
        imgNothing.setImageResource(0);
        textNothing.setText("");
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(recyclerViewTrips.getContext()));
        adapter = new HouseAdapter(houses);
        recyclerViewTrips.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showData(houseDao.getAllHouse());
    }
}