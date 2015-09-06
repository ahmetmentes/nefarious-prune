package com.mentes.nefarious_prune.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

public class ImageListActivity extends AppCompatActivity implements ImageListAdapter.ImageListListener {

    private static final String TAG = ImageListActivity.class.getSimpleName();

    private static final String TAG_NAME = "istanbul";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.image_list_recycler_view)
    RecyclerView imageListRecyclerView;

    private ImageListAdapter imageListAdapter;

    private InstagramService instagramService;

    private static String next_max_tag_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        ButterKnife.bind(this);

        arrangeToolbar();

        imageListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        imageListAdapter = new ImageListAdapter(ImageListActivity.this);
        imageListRecyclerView.setAdapter(imageListAdapter);

        instagramService = NetworkManager.getInstagramService();

        instagramService.getRecentMedia(TAG_NAME, NetworkManager.getInstagramClientId(), recentMediaCallback);

    }

    private void arrangeToolbar() {

        toolbar.setTitle(R.string.app_name);

        toolbar.inflateMenu(R.menu.menu_search);

    }

    @Override
    public void onLoadMore() {

        instagramService.getRecentMedia(TAG_NAME, NetworkManager.getInstagramClientId(), next_max_tag_id, recentMediaCallback);
    }

    Callback<RecentMedia> recentMediaCallback = new Callback<RecentMedia>() {
        @Override
        public void success(RecentMedia recentMedia, Response response) {
            next_max_tag_id = recentMedia.getPagination().getNextMaxTagId();
            imageListAdapter.addMedia(recentMedia.getMedia());
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };



}
