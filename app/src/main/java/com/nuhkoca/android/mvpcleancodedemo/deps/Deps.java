package com.nuhkoca.android.mvpcleancodedemo.deps;

import com.nuhkoca.android.mvpcleancodedemo.home.HomeActivity;
import com.nuhkoca.android.mvpcleancodedemo.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nuhkoca on 23.11.2017.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
