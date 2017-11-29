package com.nuhkoca.android.mvpcleancodedemo.di;

import com.nuhkoca.android.mvpcleancodedemo.ui.home.HomeActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

@Subcomponent(modules = ActivityBindingModule.class)
@ActivityScope
public interface HomeActivitySubComponent extends AndroidInjector<HomeActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<HomeActivity> {}
}
