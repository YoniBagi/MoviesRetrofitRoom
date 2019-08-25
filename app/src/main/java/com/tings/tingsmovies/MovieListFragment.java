package com.tings.tingsmovies;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.fragment.NavHostFragment;

import com.tings.tingsmovies.adapters.MovieListAdapter;
import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.databinding.FragmentMovieListBinding;
import com.tings.tingsmovies.managers.DataManager;

import java.util.List;


public class MovieListFragment extends Fragment implements MovieListAdapter.MovieListAdapterCallBack {
    private List<Movie>  movies;

    public MovieListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) movies = getArguments().getParcelableArrayList("listMovies");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMovieListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container,false);
        binding.setMovieListFragment(this);
        return binding.getRoot();
    }

    public MovieListAdapter getAdapter(){
        return new MovieListAdapter(movies, getContext(), this);
    }

    @Override
    public void onClickItem(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        NavHostFragment.findNavController(this).navigate(R.id.action_movieListFragment_to_detailsMovieFragment, bundle);
    }
}
