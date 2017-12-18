package com.alvin.newsapp.api;

import com.alvin.newsapp.model.ArticleResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Nakama on 12/6/2017.
 */

public interface ArticleApi {

    @GET("v2/top-headlines")
    Observable<Response<ArticleResponse>> getHeadlines(@QueryMap Map<String, String> Params);
}
