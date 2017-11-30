package com.nuhkoca.android.mvpcleancodedemo.ui.home;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;
import com.nuhkoca.android.mvpcleancodedemo.ui.BasePresenter;

/**
 * Created by Erik Duisters on 26-11-2017.
 */

final class HomeViewViewState implements BasePresenter.ViewState {
    final boolean showWait;
    @Nullable final String errorMessage;
    @Nullable final CityListResponse cityListResponse;

    HomeViewViewState(boolean showWait) {
        this(showWait, null, null);
    }

    HomeViewViewState(String errorMessage) {
        this(false, errorMessage, null);
    }

    HomeViewViewState(CityListResponse cityListResponse) {
        this(false, null, cityListResponse);
    }

    private HomeViewViewState(boolean showWait, String errorMessage, CityListResponse cityListResponse) {
        this.showWait = showWait;
        this.errorMessage = errorMessage;
        this.cityListResponse = cityListResponse;
    }
}
