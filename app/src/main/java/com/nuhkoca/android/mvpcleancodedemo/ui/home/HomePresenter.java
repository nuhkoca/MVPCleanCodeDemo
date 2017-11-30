package com.nuhkoca.android.mvpcleancodedemo.ui.home;

import com.nuhkoca.android.mvpcleancodedemo.di.ActivityScope;
import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;
import com.nuhkoca.android.mvpcleancodedemo.networking.NetworkError;
import com.nuhkoca.android.mvpcleancodedemo.networking.Service;
import com.nuhkoca.android.mvpcleancodedemo.ui.BasePresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by nuhkoca on 23.11.2017.
 */
@ActivityScope
public class HomePresenter extends BasePresenter<HomeView, HomeViewViewState> {
    private final Service service;
    private CompositeSubscription subscriptions;

    @Inject
    HomePresenter(Service service) {
        this.service = service;
        this.subscriptions = new CompositeSubscription();
    }

    private void getCityList(){
        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(CityListResponse cityListResponse) {
                handleViewState(new HomeViewViewState(cityListResponse));
            }

            @Override
            public void onError(NetworkError networkError) {
                handleViewState(new HomeViewViewState(networkError.getAppErrorMessage()));
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop(){
        subscriptions.unsubscribe();
    }

    @Override
    public void start() {
        HomeViewViewState viewState = new HomeViewViewState(true);

        handleViewState(viewState);

        getCityList();
    }
}
