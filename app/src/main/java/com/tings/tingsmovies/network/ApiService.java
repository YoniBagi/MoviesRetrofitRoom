package com.tings.tingsmovies.network;


import com.tings.tingsmovies.dataModel.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public interface ApiService {

    @GET("movies.json")
    @Headers({ "Content-Type: application/json"})
    Call<List<Movie>> getMovies();
}
