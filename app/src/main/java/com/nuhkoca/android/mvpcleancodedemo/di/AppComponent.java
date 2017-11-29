package com.nuhkoca.android.mvpcleancodedemo.di;

import com.nuhkoca.android.mvpcleancodedemo.MyApplication;
import com.nuhkoca.android.mvpcleancodedemo.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBindingModule.class, NetworkModule.class})
@Singleton
public interface AppComponent extends AndroidInjector<MyApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }
}
