package com.tings.tingsmovies.network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yonatan Bagizada on 2019-06-22.
 */
public class Repository {
    private static final String BASE_URL = "https://api.androidhive.info/json/";
    private static final Repository mInstance = new Repository();
    private static Retrofit sRetrofit;
    private ApiService sApiService;
    private static OkHttpClient okHttpClient;

    public Repository() {
        okHttpClient  = new OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS).build();
        sRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        sApiService = sRetrofit.create(ApiService.class);
    }

    public static Repository getInstance() {
        return mInstance;
    }

    public ApiService getApiService() {
        return sApiService;
    }
}
