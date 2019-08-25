package com.tings.tingsmovies.splashFragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;
import android.arch.lifecycle.Observer;

import com.tings.tingsmovies.dataModel.Movie;

import java.util.List;

public class SplashFragmentViewModel extends ViewModel {
    private SplashFragmentModel splashFragmentModel = SplashFragmentModel.getInstance();
    private MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
    private Observer observer = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            listMovies.postValue(movies);
        }
    };

    public SplashFragmentViewModel() {
        splashFragmentModel.getListLiveData().observeForever(observer);
    }

    public void fetchMovies(Context context) {
        splashFragmentModel.fetchMovies(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        splashFragmentModel.onCleared();
        splashFragmentModel.getListLiveData().removeObserver(observer);
    }

    public MutableLiveData<List<Movie>> getListMovies() {
        return listMovies;
    }
}
