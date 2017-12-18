package com.alvin.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nakama on 12/6/2017.
 */

public class ArticleResponse implements Parcelable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    public final static Parcelable.Creator<ArticleResponse> CREATOR = new Creator<ArticleResponse>() {

        public ArticleResponse createFromParcel(Parcel in) {
            return new ArticleResponse(in);
        }

        public ArticleResponse[] newArray(int size) {
            return (new ArticleResponse[size]);
        }

    };

    protected ArticleResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.articles, (ArticleResponse.class.getClassLoader()));
    }

    public ArticleResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(articles);
    }
}

