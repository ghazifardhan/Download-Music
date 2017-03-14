package com.ghazifardhan.downloadmusic.api;

import com.ghazifardhan.downloadmusic.models.Item;
import com.ghazifardhan.downloadmusic.models.Youtube;
import com.ghazifardhan.downloadmusic.models.savedeo.GrabVideo;
import com.ghazifardhan.downloadmusic.models.savedeo.PostCall;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by ghazifardhan on 05/03/17.
 */

public interface ApiService {

    // Youtube Data V3
    @GET("/youtube/v3/search")
    Call<Youtube> getData(@Query("key") String apiKey, @Query("part") String snippet, @Query("maxResults") Integer maxResults, @Query("q") String videoId);
}
