package com.tings.tingsmovies.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tings.tingsmovies.R;
import com.tings.tingsmovies.dataModel.Movie;
import com.tings.tingsmovies.databinding.ItemMovieRecyclerBinding;

import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<Movie> mMovieList;
    Context mContext;
    MovieListAdapterCallBack mMovieListAdapterCallBack;

    public MovieListAdapter(List<Movie> movieList, Context context, MovieListAdapterCallBack movieListAdapterCallBack) {
        mMovieList = movieList;
        mContext = context;
        mMovieListAdapterCallBack = movieListAdapterCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMovieRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext())
                , R.layout.item_movie_recycler, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBind(mMovieList.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMovieRecyclerBinding mBinding;

        public ViewHolder(@NonNull ItemMovieRecyclerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(Movie movie) {
            mBinding.setMovieListAdapter(this);
            Glide.with(mContext).load(movie.getImage())
                    .override(300, 300)
                    //.apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                    .apply(RequestOptions.circleCropTransform())
                    .into(mBinding.ivMovie);
            mBinding.setMovie(movie);
            mBinding.executePendingBindings();
        }

        public void onClickItem(Movie movie){
            mMovieListAdapterCallBack.onClickItem(movie);
        }
    }

    public interface MovieListAdapterCallBack{
        void onClickItem(Movie movie);
    }
}
