package com.nuhkoca.android.mvpcleancodedemo.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nuhkoca.android.mvpcleancodedemo.BaseApp;
import com.nuhkoca.android.mvpcleancodedemo.R;
import com.nuhkoca.android.mvpcleancodedemo.models.CityListData;
import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;
import com.nuhkoca.android.mvpcleancodedemo.networking.Service;

import javax.inject.Inject;

public class HomeActivity extends BaseApp implements HomeView {

    private RecyclerView recyclerView;
    @Inject
    public Service service;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getDeps().inject(this);

        renderView();
        init();

        HomePresenter homePresenter = new HomePresenter(service, this);
        homePresenter.getCityList();
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);
    }

    public void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(CityListResponse cityListResponse) {
        HomeAdapter homeAdapter = new HomeAdapter(getApplicationContext(), cityListResponse.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {
                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        recyclerView.setAdapter(homeAdapter);
    }
}