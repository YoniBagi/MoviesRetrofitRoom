package com.tings.tingsmovies.managers;

import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.network.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public class DataManager {
    private static final DataManager mInstance = new DataManager();

    public static DataManager getInstance() {
        return mInstance;
    }

//    private Movie mMovie;
    private DataCallBack mDataCallBack;
    private List<Movie> mMovieList;

    private DataManager() {
    }

    public void fetchMovie(){
        Call<List<Movie>> call = Repository.getInstance().getApiService().getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    mMovieList = response.body();
                    mDataCallBack.fetchMoviesSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                mDataCallBack.fetchMoviesFail(t);
            }
        });
    }

    /*public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }
*/
    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setDataCallBack(DataCallBack dataCallBack) {
        mDataCallBack = dataCallBack;
    }

    public interface DataCallBack{
        void fetchMoviesSuccess(List<Movie> movieList);
        void fetchMoviesFail(Throwable t);
    }
}
