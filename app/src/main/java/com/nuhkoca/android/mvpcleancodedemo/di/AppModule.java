package com.nuhkoca.android.mvpcleancodedemo.di;

import android.app.Application;
import android.content.Context;

import com.nuhkoca.android.mvpcleancodedemo.MyApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik Duisters on 25-11-2017.
 */

@Module(subcomponents = HomeActivitySubComponent.class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract Application application(MyApplication myApplication);

    @Provides
    @Singleton
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }
}
