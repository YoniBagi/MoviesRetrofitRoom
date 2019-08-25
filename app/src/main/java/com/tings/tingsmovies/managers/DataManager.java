package com.tings.tingsmovies.managers;

import android.content.Context;

import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.database.MovieRepository;
import com.tings.tingsmovies.network.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */

/**
 * A class is responsible for bringing information from the network or the data base
 */
public class DataManager {
    private static final DataManager mInstance = new DataManager();


    private DataCallBack mDataCallBack;
    private List<Movie> mMovieList;

    public static DataManager getInstance() {
        return mInstance;
    }

    public void fetchMovie(Context context) {
        final MovieRepository movieRepository = new MovieRepository(context);
        //mMovieList = movieRepository.getAllMovies();
        if (mMovieList == null || mMovieList.isEmpty()) {
            Call<List<Movie>> call = Repository.getInstance().getApiService().getMovies();
            call.enqueue(new Callback<List<Movie>>() {
                @Override
                public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                    if (response.isSuccessful()) {
                        movieRepository.insert(response.body());
                        // instead to sort I call from db and get desc order
                        // mMovieList = movieRepository.getAllMovies();
                        if (mDataCallBack != null){
                            mDataCallBack.fetchMoviesSuccess(mMovieList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Movie>> call, Throwable t) {
                    if (mDataCallBack != null) {
                        mDataCallBack.fetchMoviesFail(t);
                    }
                }
            });
        }else {
            mDataCallBack.fetchMoviesSuccess(mMovieList);
        }
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setDataCallBack(DataCallBack dataCallBack) {
        mDataCallBack = dataCallBack;
    }

    public interface DataCallBack {
        void fetchMoviesSuccess(List<Movie> movieList);

        void fetchMoviesFail(Throwable t);
    }
}
