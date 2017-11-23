package com.nuhkoca.android.mvpcleancodedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nuhkoca.android.mvpcleancodedemo.deps.DaggerDeps;
import com.nuhkoca.android.mvpcleancodedemo.deps.Deps;
import com.nuhkoca.android.mvpcleancodedemo.networking.NetworkModule;

import java.io.File;

public class BaseApp extends AppCompatActivity {
    Deps deps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}
