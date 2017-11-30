package com.nuhkoca.android.mvpcleancodedemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.util.UUID;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends BasePresenter.View<VS>, VS extends BasePresenter.ViewState, P extends BasePresenter<V, VS>>
        extends AppCompatActivity
        implements BasePresenter.View<VS> {

    private static final String KEY_VIEW_ID = "ViewID";
    private boolean isFragmentStateLocked;
    private String viewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");

        isFragmentStateLocked = true;

        if (savedInstanceState != null) {
            viewID = savedInstanceState.getString(KEY_VIEW_ID);
        } else {
            viewID = UUID.randomUUID().toString();
        }
    }

    protected abstract P getPresenter();

    protected abstract V getPresenterView();

    @Override
    public String getID() {
        return viewID;
    }

    //TODO: Maybe onResume or onResumeFragments?
    @Override
    protected void onStart() {
        getPresenter().onAttachView(getPresenterView());

        super.onStart();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

        isFragmentStateLocked = false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getPresenter().onDetachView(false);

        isFragmentStateLocked = true;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!isFragmentStateLocked) {
            getPresenter().onDetachView(isFinishing());
        }
    }
}
