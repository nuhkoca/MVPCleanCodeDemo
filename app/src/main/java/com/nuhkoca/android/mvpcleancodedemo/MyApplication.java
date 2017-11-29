package com.nuhkoca.android.mvpcleancodedemo;

import android.app.Activity;
import android.app.Application;

import com.nuhkoca.android.mvpcleancodedemo.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

public class MyApplication extends Application implements HasActivityInjector {
    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .create(this)
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
