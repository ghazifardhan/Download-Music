package com.ghazifardhan.downloadmusic.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ghazifardhan on 05/03/17.
 */

public class ApiClient {

    public static Retrofit retrofit = null;
    public static final String baseUrl = "https://www.googleapis.com/";

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
