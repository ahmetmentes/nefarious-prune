package com.mentes.nefarious_prune.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.mentes.nefarious_prune.R;
import com.mentes.nefarious_prune.adapters.ImageListAdapter;
import com.mentes.nefarious_prune.network.InstagramService;
import com.mentes.nefarious_prune.network.NetworkManager;
import com.mentes.nefarious_prune.network.models.RecentMedia;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ImageListActivity extends AppCompatActivity {

    private static final String TAG = ImageListActivity.class.getSimpleName();

    private static final String TAG_NAME = "istanbul";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.image_list_recycler_view)
    RecyclerView imageListRecyclerView;

    private ImageListAdapter imageListAdapter;

    private InstagramService instagramService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);

        imageListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        instagramService = NetworkManager.getInstagramService();

        instagramService.getRecentMedia(TAG_NAME, NetworkManager.getInstagramClientId(), new Callback<RecentMedia>() {
            @Override
            public void success(RecentMedia recentMedia, Response response) {
                Log.d(TAG, new Gson().toJson(recentMedia));
                imageListAdapter = new ImageListAdapter(recentMedia.getMedia());
                imageListRecyclerView.setAdapter(imageListAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });

    }

}
