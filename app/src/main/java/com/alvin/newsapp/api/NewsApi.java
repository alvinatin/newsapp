package com.alvin.newsapp.api;

import com.alvin.newsapp.model.Source;
import com.alvin.newsapp.model.SourceResponse;

import java.util.HashMap;
import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Nakama on 12/4/2017.
 */

public interface NewsApi {

    @GET("v2/sources")
    Observable<Response<SourceResponse>> getSources(@QueryMap HashMap<String, String> Params);

}
