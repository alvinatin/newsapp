package com.alvin.newsapp.api;

import android.util.Log;

import com.alvin.newsapp.ArticleListener;
import com.alvin.newsapp.SourceListener;
import com.alvin.newsapp.model.ArticleResponse;
import com.alvin.newsapp.model.SourceResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Nakama on 12/6/2017.
 */

public class HeadlineService extends BaseService{
    private static final String BASE_URL = "https://newsapi.org/";

    public void createService(final ArticleListener articleListener, Map<String, String> paramMap){
        paramMap.put("apiKey", API_KEY);
        ArticleApi articleApi = getRetrofit(BASE_URL).create(ArticleApi.class);

        articleApi.getHeadlines(paramMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Response<ArticleResponse>>() {
                    @Override
                    public void onNext(Response<ArticleResponse> value) {
                        articleListener.onSuccess(value.body());
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
