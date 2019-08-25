package com.tings.tingsmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.tings.tingsmovies.dataModel.Movie;

import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
@Dao
public interface MovieDao {
    @Insert
    void insertAllMovies(List<Movie> movies);

    @Query("SELECT * FROM movie_table ORDER BY mReleaseYear DESC")
    LiveData<List<Movie>> getAllMovies();
}
