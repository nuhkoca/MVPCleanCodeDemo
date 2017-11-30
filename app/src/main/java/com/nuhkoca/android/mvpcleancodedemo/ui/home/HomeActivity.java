package com.nuhkoca.android.mvpcleancodedemo.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nuhkoca.android.mvpcleancodedemo.ui.BaseActivity;
import com.nuhkoca.android.mvpcleancodedemo.R;
import com.nuhkoca.android.mvpcleancodedemo.models.CityListData;
import com.nuhkoca.android.mvpcleancodedemo.networking.Service;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity<HomeView, HomeViewViewState, HomePresenter>
        implements HomeView {

    private RecyclerView recyclerView;
    ProgressBar progressBar;

    @Inject
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected HomePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected HomeView getPresenterView() {
        return this;
    }

    public void initView() {
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.progress);
    }

    @Override
    public void render(HomeViewViewState state) {
        if (state.showWait) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }

        if (state.errorMessage != null) {
            //TODO: Implement this
        } else {
            //TODO: Implement this
        }

        if (state.cityListResponse != null) {
            HomeAdapter homeAdapter = new HomeAdapter(getApplicationContext(), state.cityListResponse.getData(),
                    new HomeAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(CityListData Item) {
                            Toast.makeText(getApplicationContext(), Item.getName(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            if (recyclerView.getAdapter() == null) {
                recyclerView.setAdapter(homeAdapter);
            } else {
                recyclerView.swapAdapter(homeAdapter, false);
            }
        }
    }
}