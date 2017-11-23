package com.nuhkoca.android.mvpcleancodedemo.home;

import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;

/**
 * Created by nuhkoca on 23.11.2017.
 */

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(CityListResponse cityListResponse);
}
