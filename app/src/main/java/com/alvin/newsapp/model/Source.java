package com.alvin.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nakama on 12/4/2017.
 */

public class Source implements Parcelable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String desc;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("language")
    @Expose
    private String lang;
    @SerializedName("country")
    @Expose
    private String country;

    public Source(){}

    private Source(Parcel in){
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        url = in.readString();
        category = in.readString();
        lang = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(url);
        dest.writeString(category);
        dest.writeString(lang);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Source> CREATOR = new Parcelable.Creator<Source>(){

        @Override
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getDesc(){return desc;}

    public void setDesc(String desc){this.desc = desc;}

    public String getUrl(){return url;}

    public void setUrl(String url){this.url = url;}

    public String getCategory(){return category;}

    public void setCategory(String category){this.category = category;}

    public String getLang(){return lang;}

    public void setLang(String lang){this.lang = lang;}

    public String getCountry(){return country;}

    public void setCountry(String country){this.country = country;}
}
