package com.tings.tingsmovies.splashFragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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

import com.tings.tingsmovies.R;
import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.databinding.FragmentSplashBinding;
import com.tings.tingsmovies.managers.DataManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SplashFragment extends Fragment implements DataManager.DataCallBack {
    private SplashFragmentViewModel splashFragmentViewModel;
    private FragmentSplashBinding fragmentSplashBinding;

    public SplashFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashFragmentViewModel = ViewModelProviders.of(this).get(SplashFragmentViewModel.class);
        splashFragmentViewModel.fetchMovies(getContext());
        splashFragmentViewModel.getListMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                fetchMoviesSuccess(movies);
            }
        });
        //DataManager.getInstance().setDataCallBack(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSplashBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        fragmentSplashBinding.setLifecycleOwner(this);
        return fragmentSplashBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fetchMovies();
    }

    @Override
    public void onStop() {
        super.onStop();
        //DataManager.getInstance().setDataCallBack(null);
    }

    private void fetchMovies() {
        DataManager.getInstance().fetchMovie(getContext());
    }

    @Override
    public void fetchMoviesSuccess(List<Movie> movieList) {
        ArrayList<Movie> movies = new ArrayList<>(movieList);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listMovies" , movies);
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_movieListFragment,bundle);
    }

    @Override
    public void fetchMoviesFail(Throwable t) {
        Log.d("Fail", t.getMessage());
    }
}
