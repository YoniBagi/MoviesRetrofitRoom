package com.tings.tingsmovies;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.tings.tingsmovies.adapters.GenreAdapter;
import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.databinding.FragmentDetailsMovieBinding;


public class DetailsMovieFragment extends Fragment {

    Movie mMovie;
    public DetailsMovieFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mMovie = getArguments().getParcelable("movie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailsMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_movie, container, false);
        binding.setDetailsMovieFragment(this);
        Glide.with(this).load(mMovie.getImage())
                .override(600, 600)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(binding.ivDetailsMovie);
        binding.setMovie(mMovie);
        binding.setDetailsMovieFragment(this);
        binding.ratingBar.setRating(mMovie.getRating());
        return binding.getRoot();
    }

    public GenreAdapter getAdapter(){
        return new GenreAdapter(mMovie.getGenre());
    }

}
