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
    private static Repository mInstance;
    private static Retrofit sRetrofit;
    private static ApiService sApiService;
    private static OkHttpClient okHttpClient;



    public static Repository getInstance() {
        if (mInstance == null){
            mInstance = new Repository();
            okHttpClient  = new OkHttpClient().newBuilder().connectTimeout(40, TimeUnit.SECONDS).build();
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            sApiService = sRetrofit.create(ApiService.class);
        }
        return mInstance;
    }

    private Repository(){
    }

    public ApiService getApiService() {
        return sApiService;
    }
}
