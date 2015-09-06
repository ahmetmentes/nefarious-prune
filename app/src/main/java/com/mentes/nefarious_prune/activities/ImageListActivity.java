package com.mentes.nefarious_prune.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.mentes.nefarious_prune.R;
import com.mentes.nefarious_prune.network.InstagramService;
import com.mentes.nefarious_prune.network.NetworkManager;
import com.mentes.nefarious_prune.network.models.RecentMedia;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImageListActivity extends AppCompatActivity {

    private static final String TAG = ImageListActivity.class.getSimpleName();


    private InstagramService instagramService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);



        instagramService = NetworkManager.getInstagramService();

        instagramService.getRecentMedia("istanbul", NetworkManager.getInstagramClientId(), new Callback<RecentMedia>() {
            @Override
            public void success(RecentMedia recentMedia, Response response) {
                Log.d(TAG, new Gson().toJson(recentMedia));

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });

    }

}
