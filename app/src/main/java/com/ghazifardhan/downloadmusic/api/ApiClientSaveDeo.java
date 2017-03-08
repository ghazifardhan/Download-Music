package com.ghazifardhan.downloadmusic.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ghazifardhan on 07/03/17.
 */

public class ApiClientSaveDeo {

    public static Retrofit retrofit = null;
    public static final String baseUrl = "https://savedeo.p.mashape.com/";

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
