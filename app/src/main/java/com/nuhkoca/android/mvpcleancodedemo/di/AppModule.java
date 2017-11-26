package com.nuhkoca.android.mvpcleancodedemo.di;

import android.app.Application;
import android.content.Context;

import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;
import com.nuhkoca.android.mvpcleancodedemo.networking.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

@Module(subcomponents = HomeActivitySubComponent.class)
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    //TODO: @Singleton
    NetworkService provideNetworkService() {
        return new NetworkService() {
            @Override
            public Observable<CityListResponse> getCityList() {
                return null;
            }
        };
    }
}
