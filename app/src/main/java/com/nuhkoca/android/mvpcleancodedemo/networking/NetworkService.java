package com.nuhkoca.android.mvpcleancodedemo.networking;

import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by nuhkoca on 23.11.2017.
 */

public interface NetworkService {

    @GET("v1/city")
    Observable<CityListResponse> getCityList();
}
