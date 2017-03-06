package com.ghazifardhan.downloadmusic.api;

import com.ghazifardhan.downloadmusic.models.Item;
import com.ghazifardhan.downloadmusic.models.Youtube;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ghazifardhan on 05/03/17.
 */

public interface ApiService {

    @GET("/youtube/v3/search")
    Call<Youtube> getData(@Query("key") String apiKey, @Query("part") String snippet, @Query("q") String videoId);

}
