package com.tings.tingsmovies;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.databinding.FragmentSplashBinding;
import com.tings.tingsmovies.managers.DataManager;

import java.util.List;


public class SplashFragment extends Fragment implements DataManager.DataCallBack {

    private FragmentSplashBinding fragmentSplashBinding;

    public SplashFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManager.getInstance().setDataCallBack(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSplashBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash, container, false);
        fragmentSplashBinding.setLifecycleOwner(this);
        return fragmentSplashBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fetchMovies();
    }

    private void fetchMovies() {
        DataManager.getInstance().fetchMovie();
    }

    @Override
    public void fetchMoviesSuccess(List<Movie> movieList) {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_movieListFragment);
    }

    @Override
    public void fetchMoviesFail(Throwable t) {
        Log.d("Fail", t.getMessage());
    }
}
