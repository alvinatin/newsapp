package com.alvin.newsapp.api;

import android.util.Log;

import com.alvin.newsapp.SourceListener;
import com.alvin.newsapp.model.SourceResponse;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Nakama on 12/6/2017.
 */

public class NewsSourceService extends BaseService {

    private static final String BASE_URL = "https://newsapi.org/";

    public void createService(final SourceListener sourceListener){
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("apiKey", API_KEY);
        NewsApi newsApi = getRetrofit(BASE_URL).create(NewsApi.class);

        newsApi.getSources(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<SourceResponse>>() {
                    @Override
                    public void onNext(Response<SourceResponse> value) {
                        sourceListener.onSuccess(value.body());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("gagal", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
