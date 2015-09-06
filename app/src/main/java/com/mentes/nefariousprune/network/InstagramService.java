package com.mentes.nefariousprune.network;

import com.mentes.nefariousprune.network.models.RecentMedia;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ahmetmentes on 06/09/15.
 */
public interface InstagramService {

    @GET("/tags/{tag-name}/media/recent")
    void getRecentMedia(
            @Path("tag-name") String tag,
            @Query("client_id") String clientId,
            Callback<RecentMedia> callback
    );

    @GET("/tags/{tag-name}/media/recent")
    void getRecentMedia(
            @Path("tag-name") String tag,
            @Query("client_id") String clientId,
            @Query("max_tag_id") String maxTagId,
            Callback<RecentMedia> callback
    );
}
