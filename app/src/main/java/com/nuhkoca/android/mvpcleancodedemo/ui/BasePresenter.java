package com.nuhkoca.android.mvpcleancodedemo.ui;

import android.support.v4.util.SimpleArrayMap;

import com.nuhkoca.android.mvpcleancodedemo.util.LogUtil;

/**
 * Created by Erik Duisters on 19-02-2017.
 */

public abstract class BasePresenter<V extends BasePresenter.View<VS>, VS extends BasePresenter.ViewState> {
    private final String TAG = getClass().getSimpleName();

    protected V view;
    protected String viewID;
    private static SimpleArrayMap<String, ViewState> viewStateMap = new SimpleArrayMap<>();

    public void onAttachView(V view) {
        LogUtil.v(TAG, "onAttachView()");
        this.view = view;
        this.viewID = view.getID();

        VS state = getViewState();

        if (state != null) {
            handleViewState(state);
        } else {
            start();
        }
    }

    public void onDetachView(boolean discardState) {
        LogUtil.v(TAG, "onDetachView()");

        if(discardState && view != null) {
            viewStateMap.remove(viewID);
        }

        this.view = null;
    }

    protected void handleViewState(VS viewState) {
        viewStateMap.put(viewID, viewState);

        if (view != null) {
            view.render(viewState);
        }
    }

    protected VS getViewState() {
        //noinspection unchecked
        return (VS) viewStateMap.get(viewID);
    }

    protected abstract void start();

    public interface ViewState {
    }

    public interface View<VS extends ViewState> {
        String getID();
        void render(VS state);
    }
}
