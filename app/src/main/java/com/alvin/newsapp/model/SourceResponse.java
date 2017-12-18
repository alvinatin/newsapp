package com.alvin.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nakama on 12/4/2017.
 */

public class SourceResponse implements Parcelable{
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("sources")
    @Expose
    private List<Source> sources;

    public String getStatus(){ return status;}

    public void setStatus(String status) {this.status = status;}

    public List<Source> getSources(){return sources;}

    public void setSources(List<Source> sources){this.sources = sources;}

    public SourceResponse(Parcel in){

        status = in.readString();
        sources = in.createTypedArrayList(Source.CREATOR);
    }

    public static final Creator<SourceResponse> CREATOR = new Creator<SourceResponse>() {
        @Override
        public SourceResponse createFromParcel(Parcel in) {
            return new SourceResponse(in);
        }

        @Override
        public SourceResponse[] newArray(int size) {
            return new SourceResponse[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeTypedList(sources);
    }
}
