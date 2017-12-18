package com.alvin.newsapp;

import com.alvin.newsapp.model.SourceResponse;

/**
 * Created by Nakama on 12/5/2017.
 */

public interface SourceListener {
    void onSuccess(SourceResponse sourceResponse);
}
