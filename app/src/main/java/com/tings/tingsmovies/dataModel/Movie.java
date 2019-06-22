package com.tings.tingsmovies.dataModel;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
@Entity
public class Movie implements Parcelable {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("image")
    private String mImage;
    @SerializedName("rating")
    private float mRating;
    @SerializedName("releaseYear")
    private int mReleaseYear;
    @SerializedName("genre")
    private List<String> mGenre;

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mImage = in.readString();
        mRating = in.readFloat();
        mReleaseYear = in.readInt();
        mGenre = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mImage);
        dest.writeFloat(mRating);
        dest.writeInt(mReleaseYear);
        dest.writeStringList(mGenre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getImage() {
        return mImage;
    }

    public float getRating() {
        return mRating;
    }

    public int getReleaseYear() {
        return mReleaseYear;
    }

    public List<String> getGenre() {
        return mGenre;
    }
}
