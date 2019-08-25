package com.tings.tingsmovies.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.tings.tingsmovies.dataModel.Movie;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
@Database(entities = {Movie.class}, version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase sInstance;

    public abstract MovieDao mMovieDao();

    public static synchronized MovieDatabase getInstance(Context mContext){
        if (sInstance == null){
            sInstance = Room.databaseBuilder(mContext.getApplicationContext(),
                    MovieDatabase.class, "movie_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(sCallback)
                    .build();
        }
        return sInstance;
    }

    private static RoomDatabase.Callback sCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
