package com.tings.tingsmovies.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public class ConverterListString {
    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}

