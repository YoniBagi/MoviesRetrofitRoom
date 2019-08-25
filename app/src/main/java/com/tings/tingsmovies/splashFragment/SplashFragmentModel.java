package com.tings.tingsmovies.splashFragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.database.MovieRepository;
import com.tings.tingsmovies.network.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashFragmentModel {
    private static SplashFragmentModel instance;
    private MutableLiveData<List<Movie>> listLiveData = new MutableLiveData<>();
    private MovieRepository movieRepository;
    private Observer observer = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable final List<Movie> movies) {
            if (movies == null || movies.isEmpty()) {
                Call<List<Movie>> call = Repository.getInstance().getApiService().getMovies();
                call.enqueue(new Callback<List<Movie>>() {
                    @Override
                    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                        if (response.isSuccessful()) {
                            movieRepository.insert(response.body());
                            // instead to sort I call from db and get desc order
                            // mMovieList = movieRepository.getAllMovies();
                            /*if (mDataCallBack != null){
                                mDataCallBack.fetchMoviesSuccess(mMovieList);
                            }*/
                            listLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Movie>> call, Throwable t) {
                        /*if (mDataCallBack != null) {
                            mDataCallBack.fetchMoviesFail(t);
                        }*/
                    }
                });
            }else listLiveData.postValue(movies);
        }
    };
    private SplashFragmentModel() {
    }

    public static SplashFragmentModel getInstance() {
        if (instance == null) {
            instance = new SplashFragmentModel();
        }
        return instance;
    }

    public void fetchMovies(Context context){
        movieRepository = new MovieRepository(context);
        movieRepository.getAllMovies().observeForever(observer);
    }

    public LiveData<List<Movie>> getListLiveData() {
        return listLiveData;
    }

    public void onCleared() {
        movieRepository.getAllMovies().removeObserver(observer);
    }
}
