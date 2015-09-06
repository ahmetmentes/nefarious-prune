package com.mentes.nefariousprune.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mentes.nefariousprune.R;
import com.mentes.nefariousprune.adapters.ImageListAdapter;
import com.mentes.nefariousprune.network.InstagramService;
import com.mentes.nefariousprune.network.NetworkManager;
import com.mentes.nefariousprune.network.models.RecentMedia;

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

    private static String nextMaxTagId;
    private static String tagName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        ButterKnife.bind(this);

        arrangeToolbar();

        imageListRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        imageListAdapter = new ImageListAdapter(ImageListActivity.this);
        imageListRecyclerView.setAdapter(imageListAdapter);

        tagName = TAG_NAME;

        instagramService = NetworkManager.getInstagramService();

        fetchRecentMedia();
    }

    private void arrangeToolbar() {

        toolbar.setTitle(R.string.app_name);

        toolbar.inflateMenu(R.menu.menu_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(toolbar.getMenu().findItem(R.id.action_search));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tagName = query;
                fetchRecentMedia();
                imageListAdapter.clearData();
                fetchRecentMedia();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void fetchRecentMedia() {
        instagramService.getRecentMedia(tagName, NetworkManager.getInstagramClientId(), recentMediaCallback);
    }

    @Override
    public void onLoadMore() {
        instagramService.getRecentMedia(tagName, NetworkManager.getInstagramClientId(), nextMaxTagId, recentMediaCallback);
    }

    @Override
    public void onImageClicked(View view, String imageUrl) {
        Intent intent = FullImageActivity.newIntent(this,imageUrl);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view.findViewById(R.id.image), FullImageActivity.IMAGE_URL);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    private Callback<RecentMedia> recentMediaCallback = new Callback<RecentMedia>() {
        @Override
        public void success(RecentMedia recentMedia, Response response) {
            nextMaxTagId = recentMedia.getPagination().getNextMaxTagId();
            imageListAdapter.addMedia(recentMedia.getMedia());
        }


        @Override
        public void failure(RetrofitError error) {
            Snackbar.make(imageListRecyclerView, R.string.an_error_occurred,Snackbar.LENGTH_SHORT)
                    .show();
        }
    };



}
