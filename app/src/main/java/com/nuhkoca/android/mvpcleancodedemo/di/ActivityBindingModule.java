package com.nuhkoca.android.mvpcleancodedemo.di;

import android.app.Activity;

import com.nuhkoca.android.mvpcleancodedemo.ui.home.HomeActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

@Module
public abstract class ActivityBindingModule {
    @Binds
    @IntoMap
    @ActivityKey(HomeActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindHomeActivity(HomeActivitySubComponent.Builder builder);

    // Other Activities go here
}
