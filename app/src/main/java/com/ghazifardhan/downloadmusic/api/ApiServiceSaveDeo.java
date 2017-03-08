package com.ghazifardhan.downloadmusic.api;

import com.ghazifardhan.downloadmusic.models.savedeo.GrabVideo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by ghazifardhan on 07/03/17.
 */

public interface ApiServiceSaveDeo {
    // Savedeo
    @FormUrlEncoded
    @POST("/download")
    Call<GrabVideo> getDataVideo(@Header("X-Mashape-Key") String apiKey, @Field("url") String url);
}
