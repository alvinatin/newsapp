package com.alvin.newsapp;

import com.alvin.newsapp.model.ArticleResponse;

/**
 * Created by Nakama on 12/6/2017.
 */

public interface ArticleListener {
    void onSuccess(ArticleResponse articleResponse);
}
