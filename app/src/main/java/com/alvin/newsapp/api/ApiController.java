package com.alvin.newsapp.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alvin.newsapp.SourceListener;
import com.alvin.newsapp.model.Source;
import com.alvin.newsapp.model.SourceResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nakama on 12/4/2017.
 */

public class ApiController {
//
//    public void loadSource() {
//        Retrofit retrofit = getRetrofit();
//        HashMap<String, String> paramMap = new HashMap<>();
//        paramMap.put("apiKey", API_KEY);
//        NewsApi newsApi = retrofit.create(NewsApi.class);
//
//
//        newsApi.getSources(paramMap)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<Response<SourceResponse>>() {
//                    @Override
//                    public void onNext(Response<SourceResponse> value) {
//                        sourceListener.onSuccess(value.body());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("gagal", e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//
//
//
//    public void loadHeadline(){
//
//    }
}
