package com.tings.tingsmovies.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tings.tingsmovies.R;
import com.tings.tingsmovies.databinding.ItemGenreRecyclerBinding;

import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-06-23.
 */
public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private List<String> mGenreList;

    public GenreAdapter(List<String> genreList) {
        mGenreList = genreList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemGenreRecyclerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_genre_recycler, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(mGenreList.get(i));
    }

    @Override
    public int getItemCount() {
        return mGenreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemGenreRecyclerBinding mBinding;
        public ViewHolder(@NonNull ItemGenreRecyclerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(String s) {
            mBinding.setGenreAdapter(this);
            mBinding.setGenre(s);
            mBinding.executePendingBindings();
        }
    }
}
