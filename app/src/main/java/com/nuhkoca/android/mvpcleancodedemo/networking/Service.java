package com.nuhkoca.android.mvpcleancodedemo.networking;

import com.nuhkoca.android.mvpcleancodedemo.models.CityListResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by nuhkoca on 23.11.2017.
 */

//TODO: @Singleton
public class Service{
    private final NetworkService networkService;

    @Inject
    public Service(NetworkService networkService)
    {
        this.networkService = networkService;
    }

    public Subscription getCityList(final GetCityListCallback cityListCallback){
        return networkService.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityListResponse>>() {
                    @Override
                    public Observable<? extends CityListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        cityListCallback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(CityListResponse cityListResponse) {
                        cityListCallback.onSuccess(cityListResponse);
                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(CityListResponse cityListResponse);
        void onError(NetworkError networkError);
    }
}
