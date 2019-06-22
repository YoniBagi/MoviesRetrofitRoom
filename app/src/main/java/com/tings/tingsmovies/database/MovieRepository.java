package com.tings.tingsmovies.database;

import android.content.Context;
import android.os.AsyncTask;

import com.tings.tingsmovies.dataModel.Movie;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public class MovieRepository {
    private MovieDao mMovieDao;

    public MovieRepository(Context context) {
        MovieDatabase movieDatabase = MovieDatabase.getInstance(context);
        mMovieDao = movieDatabase.mMovieDao();
    }

    public void insert(List<Movie> movies){
        new InsertMoviesAsyncTask(mMovieDao).execute(movies);
    }

    public List<Movie> getAllMovies() {
        try {
            return new GetMoviesAsyncTask(mMovieDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InsertMoviesAsyncTask extends AsyncTask< List<Movie>, Void, Void> {
        private MovieDao mMovieDao;

        private InsertMoviesAsyncTask(MovieDao movieDao) {
            mMovieDao = movieDao;
        }

        @Override
        protected Void doInBackground(List<Movie>... lists) {
            mMovieDao.insertAllMovies(lists[0]);
            return null;
        }
    }

    private static class GetMoviesAsyncTask extends AsyncTask<Void, Void, List<Movie>>{
        private MovieDao mMovieDao;

        public GetMoviesAsyncTask(MovieDao movieDao) {
            mMovieDao = movieDao;
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            return mMovieDao.getAllMovies();
        }
    }
}
